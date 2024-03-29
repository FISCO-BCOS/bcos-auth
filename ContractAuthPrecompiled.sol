// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

contract ContractAuthPrecompiled {
    function getAdmin(address contractAddr) public view returns (address) {}

    function resetAdmin(address contractAddr, address admin)
        public
        returns (int256)
    {}

    function setMethodAuthType(
        address contractAddr,
        bytes4 funcSelector,
        uint8 authType
    ) public returns (int256) {}

    function openMethodAuth(
        address contractAddr,
        bytes4 funcSelector,
        address account
    ) public returns (int256) {}

    function closeMethodAuth(
        address contractAddr,
        bytes4 funcSelector,
        address account
    ) public returns (int256) {}

    function checkMethodAuth(
        address contractAddr,
        bytes4 funcSelector,
        address account
    ) public view returns (bool) {}

    function getMethodAuth(address path, bytes4 funcSelector)
        public
        view
        returns (
            uint8,
            string[] memory,
            string[] memory
        )
    {}

    function setContractStatus(address _address, bool isFreeze)
        public
        returns (int256)
    {}

    function contractAvailable(address _address) public view returns (bool) {}

    function deployType() public view returns (uint256) {}

    function setDeployAuthType(uint8 _type) public returns (int256) {}

    function openDeployAuth(address account) public returns (int256) {}

    function closeDeployAuth(address account) public returns (int256) {}

    function hasDeployAuth(address account) public view returns (bool) {}
}
