// SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.6.0;

import "./LibAddressSet.sol";
import "./BasicAuth.sol";

contract Committee is BasicAuth {
    using LibAddressSet for LibAddressSet.AddressSet;

    LibAddressSet.AddressSet private _governorSet;
    mapping(address => uint32) private _weightMapping;
    uint8 public _participatesRate;
    uint8 public _winRate;

    constructor(
        address[] memory governorList,
        uint32[] memory weightList,
        uint8 participatesRate,
        uint8 winRate
    ) public {
        for (uint32 i = 0; i < governorList.length; i++) {
            setWeight(governorList[i], weightList[i]);
        }
        _winRate = winRate;
        _participatesRate = participatesRate;
        setOwner(msg.sender);
    }

    /*
     * set rate by owner
     * @param participate rate
     * @param win rate
     */
    function setRate(uint8 participatesRate, uint8 winRate) public onlyOwner {
        _winRate = winRate;
        _participatesRate = participatesRate;
    }

    /*
     * set weight only by owner
     * @param governor external address
     * @param weight
     */
    function setWeight(address governor, uint32 weight) public onlyOwner {
        if (weight == 0) {
            require(governor != tx.origin, "You can not remove yourself!");
            delete _weightMapping[governor];
            _governorSet.remove(governor);
        } else if (_governorSet.contains(governor)) {
            _weightMapping[governor] = weight;
        } else {
            _weightMapping[governor] = weight;
            _governorSet.add(governor);
        }
    }

    /*
     * get committee info
     */
    function getCommitteeInfo()
        public
        view
        returns (
            uint8 participatesRate,
            uint8 winRate,
            address[] memory governors,
            uint32[] memory weights
        )
    {
        governors = _governorSet.getAll();
        weights = new uint32[](governors.length);
        for (uint256 i = 0; i < governors.length; i++) {
            weights[i] = _weightMapping[governors[i]];
        }
        winRate = _winRate;
        participatesRate = _participatesRate;
    }

    /*
     * predicate governor
     * @param governor address
     */
    function isGovernor(address governor) public view returns (bool) {
        return _governorSet.contains(governor);
    }

    /*
     * get weight
     * @param governor address
     */
    function getWeight(address governor) public view returns (uint32) {
        return _weightMapping[governor];
    }

    /*
     * predicate vote result and return the status
     * @param for voters list
     * @param against voters list
     */
    function determineVoteResult(
        address[] memory agreeVoters,
        address[] memory againstVoters
    ) public view returns (uint8) {
        uint32 agreeVotes = getWeights(agreeVoters);
        uint32 doneVotes = agreeVotes + getWeights(againstVoters);
        uint32 allVotes = getWeights(_governorSet.getAll());
        //1. Checks enough voters: totalVotes/totalVotesPower >= p_rate/100
        if (doneVotes * 100 < allVotes * _participatesRate) {
            //not enough voters, need more votes
            return 1;
        }
        //2. Checks whethere for votes wins: agreeVotes/totalVotes >= win_rate/100
        if (agreeVotes * 100 >= _winRate * doneVotes) {
            return 2;
        } else {
            return 3;
        }
    }

    /*
     * compute weights with given votes list
+     * @param computed voters list
     */
    function getWeights(address[] memory votes) public view returns (uint32) {
        uint32 totalVotes = 0;
        for (uint32 i = 0; i < votes.length; i++) {
            totalVotes += _weightMapping[votes[i]];
        }
        return totalVotes;
    }
}
