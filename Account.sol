// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

enum AccountStatus {
    normal,
    freeze
}

abstract contract AccountManager {
    // 设置账户状态，只有治理委员可以调用，0 - normal, others - abnormal
    function setAccountStatus(address addr, AccountStatus status)
        public
        virtual
        returns (int32);

    // 任何用户都可以调用
    function getAccountStatus(address addr)
        public
        view
        virtual
        returns (AccountStatus);
}
