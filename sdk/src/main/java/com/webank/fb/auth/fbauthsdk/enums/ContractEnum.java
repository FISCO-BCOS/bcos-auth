package com.webank.fb.auth.fbauthsdk.enums;

import lombok.Getter;

@Getter
public enum ContractEnum {

    COMMITTEE_MANAGER("CommitteeManager"),
    PROPOSAL_MANAGER("ProposalManager"),
    COMMITTEE("Committee"),
    DEPLOY_AUTH_MANAGER("DeployAuthManager"),
    METHOD_AUTH_MANAGER("MethodAuthManager");

    private final String value;

    ContractEnum(String value) {
        this.value = value;
    }
}
