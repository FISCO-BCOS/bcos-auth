pragma solidity ^0.4.25;
//pragma experimental ABIEncoderV2;

import "./Committee.sol";
import "./ProposalManager.sol";
import "./DeployAuthManager.sol";
import "./MethodAuthManager.sol";

contract CommitteeManager {
    // Governors and threshold
    Committee public _committee;
    // proposal manager
    ProposalManager public _proposalMgr;
    // deploy auth manager
    DeployAuthManager public _deployAuthMgr;

    struct ProposalInfo {
        // 11-set governor weight; 12-set rate; 21-set deploy auth type; 22-modify deploy auth; 31-reset admin
        uint8 proposalType;
        // unique address
        address resourceId;
        // uint8 array
        uint8[] uint8Array;
        uint32 weight;
        // address array
        address[] addressArray;
        bool flag;
    }
    // [id, Proposal]
    mapping(uint256 => ProposalInfo) private _proposalInfoMap;

    modifier onlyGovernor() {
        require(isGovernor(msg.sender), "you must be governor");
        _;
    }

    constructor(
        address[] initGovernors,
        uint32[] weights,
        uint8 participatesRate,
        uint8 winRate
    ) public {
        _committee = new Committee(
            initGovernors,
            weights,
            participatesRate,
            winRate
        );
        _proposalMgr = new ProposalManager(address(this), address(_committee));
        _deployAuthMgr = new DeployAuthManager(address(this));
    }

    /*
     * apply for update governor
     * @param external account
     * @param weight, 0-delete, >0-update or insert
     * @param blockNumberInterval, after current block number, it will be outdated.
     */
    function createUpdateGovernorProposal(
        address account,
        uint32 weight,
        uint256 blockNumberInterval
    ) public onlyGovernor returns (uint256 currentproposalId) {
        address[] memory addressArray = new address[](1);
        uint8[] memory uint8Array;
        addressArray[0] = account;
        ProposalInfo memory proposalInfo = ProposalInfo(
            11,
            account,
            uint8Array,
            weight,
            addressArray,
            true
        );
        currentproposalId = _createProposal(proposalInfo, blockNumberInterval);
    }

    /*
     * apply set participate rate and win rate.
     * @param paricipate rate, [0,100]. if 0, always succeed.
     * @param win rate, [0,100].
     * @param blockNumberInterval, after current block number, it will be outdated.
     */
    function createSetRateProposal(
        uint8 participatesRate,
        uint8 winRate,
        uint256 blockNumberInterval
    ) public onlyGovernor returns (uint256 currentproposalId) {
        require(
            participatesRate >= 0 && participatesRate <= 100,
            "invalid range of participatesRate"
        );
        require(winRate >= 0 && winRate <= 100, "invalid range of winRate");
        address[] memory addressArray;
        uint8[] memory uint8Array = new uint8[](2);
        uint8Array[0] = participatesRate;
        uint8Array[1] = winRate;
        ProposalInfo memory proposalInfo = ProposalInfo(
            12,
            address(this),
            uint8Array,
            0,
            addressArray,
            false
        );
        currentproposalId = _createProposal(proposalInfo, blockNumberInterval);
    }

    /*
     * submit an proposal of setting deploy contract auth type
     * @param deployAuthType: 1- whitelist; 2-blacklist
     */
    function createSetDeployAuthTypeProposal(
        uint8 deployAuthType,
        uint256 blockNumberInterval
    ) public onlyGovernor returns (uint256 currentproposalId) {
        require(
            _deployAuthMgr._deployAuthType() != deployAuthType,
            "the current deploy auth type is the same as you want to set"
        );

        address[] memory addressArray;
        uint8[] memory uint8Array = new uint8[](1);
        uint8Array[0] = deployAuthType;
        ProposalInfo memory proposalInfo = ProposalInfo(
            21,
            address(_deployAuthMgr),
            uint8Array,
            0,
            addressArray,
            false
        );
        currentproposalId = _createProposal(proposalInfo, blockNumberInterval);
    }

    /*
     * submit an proposal of adding deploy contract auth for account
     * @param account
     * @param openFlag: true-open; false-close
     */
    function createModifyDeployAuthProposal(
        address account,
        bool openFlag,
        uint256 blockNumberInterval
    ) public onlyGovernor returns (uint256 currentproposalId) {
        require(
            openFlag && !_deployAuthMgr.hasDeployAuth(account),
            "account has the auth of deploying contract."
        );
        require(
            !openFlag && _deployAuthMgr.hasDeployAuth(account),
            "account has no auth of deploying contract."
        );

        address[] memory addressArray = new address[](1);
        addressArray[0] = account;
        uint8[] memory uint8Array;
        ProposalInfo memory proposalInfo = ProposalInfo(
            22,
            account,
            uint8Array,
            0,
            addressArray,
            openFlag
        );
        currentproposalId = _createProposal(proposalInfo, blockNumberInterval);
    }

    /*
     * submit an propsal of resetting contract admin
     * @param newAdmin
     * @param methodAuthAddr
     */
    function createResetAdminProposal(
        address newAdmin,
        address methodAuthAddr,
        uint256 blockNumberInterval
    ) public onlyGovernor returns (uint256 currentproposalId) {
        MethodAuthManager methodAuthMgr = MethodAuthManager(methodAuthAddr);

        require(
            methodAuthMgr != address(0),
            "method auth manager contract not exists."
        );
        require(methodAuthMgr._owner() == address(this), "caller is not owner");
        require(
            newAdmin != methodAuthMgr._admin(),
            "the account has been the admin of concurrt contract."
        );
        address[] memory addressArray = new address[](2);
        uint8[] memory uint8Array;
        addressArray[0] = methodAuthAddr;
        addressArray[1] = newAdmin;
        ProposalInfo memory proposalInfo = ProposalInfo(
            31,
            methodAuthMgr._contractAddress(),
            uint8Array,
            0,
            addressArray,
            false
        );
        currentproposalId = _createProposal(proposalInfo, blockNumberInterval);
    }

    /*
     * create proposal
     * @param create address
     * @param  proposal type : 1X-committee；2X-deploy contract auth；3X-admin auth
     * @param resource id
     * @param  after the block number interval, the proposal would be outdated.
     */
    function _createProposal(
        ProposalInfo proposalInfo,
        uint256 blockNumberInterval
    ) internal returns (uint256) {
        uint256 proposalId = _proposalMgr.create(
            msg.sender,
            proposalInfo.proposalType,
            proposalInfo.resourceId,
            blockNumberInterval
        );
        _proposalInfoMap[proposalId] = proposalInfo;
        //detault vote agree for the proposal.
        voteProposal(proposalId, true);
        return proposalId;
    }

    /*
     * revoke proposal
     * @param proposal id
     */
    function revokeProposal(uint256 proposalId) public onlyGovernor {
        _proposalMgr.revoke(proposalId, msg.sender);
    }

    /*
     * unified vote
     * @param proposal id
     * @param true or false
     */
    function voteProposal(uint256 proposalId, bool agree) public onlyGovernor {
        uint8 voteStatus = _proposalMgr.vote(proposalId, agree, msg.sender);
        ProposalInfo memory proposalInfo;
        if (voteStatus == 2) {
            uint8 proposalType = getProposalType(proposalId);
            proposalInfo = _proposalInfoMap[proposalId];
            if (proposalType == 11) {
                _committee.setWeight(
                    proposalInfo.addressArray[0],
                    proposalInfo.weight
                );
            } else if (proposalType == 12) {
                _committee.setRate(
                    proposalInfo.uint8Array[0],
                    proposalInfo.uint8Array[1]
                );
            } else if (proposalType == 21) {
                _deployAuthMgr.setDeployAuthType(proposalInfo.uint8Array[0]);
            } else if (proposalType == 22) {
                if (proposalInfo.flag) {
                    _deployAuthMgr.openDeployAuth(proposalInfo.addressArray[0]);
                } else {
                    _deployAuthMgr.closeDeployAuth(
                        proposalInfo.addressArray[0]
                    );
                }
            } else if (proposalType == 31) {
                MethodAuthManager methodAuthMgr = MethodAuthManager(
                    proposalInfo.addressArray[0]
                );
                methodAuthMgr.resetAdmin(proposalInfo.addressArray[1]);
            } else {
                revert("vote type error.");
            }
        }
    }

    /*
     * when deploying a contract by user, system should deploy the corresponding contract for managing the methods auth.
     * @param contractAddr: contract address
     * @parma admin: admin who can manage the contract methods auth.
     */
    function deployMethodAuthMgrContract(address contractAddr, address admin)
        public
        returns (address)
    {
        return new MethodAuthManager(contractAddr, admin, address(this));
    }

    /*
     * predicate governor
     * @param external account
     */
    function isGovernor(address account) public view returns (bool) {
        return _committee.isGovernor(account);
    }

    /*
     * get proposal type
     * @param proposal id
     */
    function getProposalType(uint256 proposalId) public view returns (uint8) {
        return _proposalInfoMap[proposalId].proposalType;
    }
}
