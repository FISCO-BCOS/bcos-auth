pragma solidity ^0.4.24;

contract ContractAuthPrecompiled {
    function getAdmin(address contractAddr) public view returns (address);

    function resetAdmin(address contractAddr, address admin)
        public
        returns (int256);

    function setMethodAuthType(
        address contractAddr,
        bytes4 func,
        uint8 authType
    ) public returns (int256);

    function openMethodAuth(
        address contractAddr,
        bytes4 func,
        address account
    ) public returns (int256);

    function closeMethodAuth(
        address contractAddr,
        bytes4 func,
        address account
    ) public returns (int256);

    function checkMethodAuth(
        address contractAddr,
        bytes4 func,
        address account
    ) public returns (bool);
}
