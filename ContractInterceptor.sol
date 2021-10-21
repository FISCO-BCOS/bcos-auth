pragma solidity ^0.4.25;

import "./DeployAuthManager.sol";
import "./MethodAuthManager.sol";

contract ContractInterceptor {
    DeployAuthManager private _deployAuthMgr;

    constructor(address deployAuthMgr) public {
        _deployAuthMgr = DeployAuthManager(deployAuthMgr);
    }

    //function login(address account) public view {}

    //function logout(address account) public view {}

    /*
     * when deploy contract, create function will check the deploy contract auth firstly.
     * @param account
     */
    function create(address account) public view returns (bool) {
        return _deployAuthMgr.hasDeployAuth(account);
    }

    /*
     * when call method, call function will check the method access auth firstly.
     * @param methodAuthMgrAddr
     * @param methodId
     * @param account
     */
    function call(
        address methodAuthMgrAddr,
        bytes4 methodId,
        address account
    ) public view returns(bool) {
        return checkAccessMethodAuth(methodAuthMgrAddr, methodId, account);
    }

    /*
     * when send transaction by the method, sendTransaction function will check the method access auth firstly.
     * @param methodAuthMgrAddr
     * @param methodId
     * @param account
     */
    function sendTransaction(
        address methodAuthMgrAddr,
        bytes4 methodId,
        address account
    ) public view returns (bool) {
        return checkAccessMethodAuth(methodAuthMgrAddr, methodId, account);
    }

    /*
     * when access the method, checkAccessMethodAuth function will check the method access auth firstly.
     * @param methodAuthMgrAddr
     * @param methodId
     * @param account
     */
    function checkAccessMethodAuth(
        address methodAuthMgrAddr,
        bytes4 methodId,
        address account
    ) internal view returns (bool) {
        MethodAuthManager mgr = MethodAuthManager(methodAuthMgrAddr);
        return mgr.hasMethodAccessAuth(methodId, account);
    }
}
