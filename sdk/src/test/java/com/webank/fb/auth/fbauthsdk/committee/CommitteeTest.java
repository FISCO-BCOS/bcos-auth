package com.webank.fb.auth.fbauthsdk.committee;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import com.webank.fb.auth.fbauthsdk.BaseTest;
import com.webank.fb.auth.fbauthsdk.contracts.Committee;
import com.webank.fb.auth.fbauthsdk.contracts.CommitteeManager;
import com.webank.fb.auth.fbauthsdk.contracts.DeployAuthManager;
import com.webank.fb.auth.fbauthsdk.contracts.MethodAuthManager;
import com.webank.fb.auth.fbauthsdk.enums.ContractEnum;
import org.fisco.bcos.sdk.abi.ABICodecException;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.fisco.bcos.sdk.transaction.model.exception.TransactionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CommitteeTest extends BaseTest {
    private CommitteeManager committeeManager;
    private Committee committee;
    private DeployAuthManager deployAuthManager;

    @PostConstruct
    public void deploy() throws Exception {
        AssembleTransactionProcessor txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(client,u1);
        List<Object> params = new ArrayList<>();
        List<String> governors = new ArrayList<>();
        governors.add(u1.getAddress());
        governors.add(u2.getAddress());
        governors.add(u3.getAddress());
        List<BigInteger> weights = new ArrayList<>();
        weights.add(BigInteger.ONE);
        weights.add(BigInteger.ONE);
        weights.add(BigInteger.ONE);
        params.add(governors);
        params.add(weights);
        params.add(BigInteger.valueOf(10));
        params.add(BigInteger.valueOf(40));

        /*TransactionResponse response = txProcessor.deployAndGetResponse(abiBinConfig.getAbiMap().get(ContractEnum.COMMITTEE_MANAGER.getValue()),
                abiBinConfig.getBinMap().get(ContractEnum.COMMITTEE_MANAGER.getValue()),
                params);
        System.out.println(JsonUtils.toJson(response));*/
        committeeManager = CommitteeManager.deploy(client, u1, governors, weights, BigInteger.valueOf(10), BigInteger.valueOf(40));
        Console.log("committee manager address is {}" , committeeManager.getContractAddress());
        Console.log("Committee address is {}",committeeManager._committee() );
        Console.log("deploy auth manager address is {}",committeeManager._deployAuthMgr() );
        Console.log("proposal manager address is {}",committeeManager._proposalMgr() );
        committee = Committee.load(committeeManager._committee(), client, u1);
        deployAuthManager = DeployAuthManager.load(committeeManager._deployAuthMgr(), client, u1);
    }

    @Test
    @Order(10)
    public void testUpdateGovernor() throws ContractException, ABICodecException, TransactionException, IOException {
        Console.log("Committee address is {}",committeeManager._committee() );

        Console.log(JSONUtil.parseObj(committee.getCommitteeInfo()).toStringPretty());
        // creat proposal
        TransactionReceipt tr = committeeManager.createUpdateGovernorProposal(u4.getAddress(), BigInteger.valueOf(2), blockNumberInterval);
        TransactionResponse response = decoder.decodeReceiptWithValues(CommitteeManager.ABI, CommitteeManager.FUNC_CREATEUPDATEGOVERNORPROPOSAL, tr);
        Console.log(JSONUtil.parseObj(response).toStringPretty());
        // vote
        committeeManager.voteProposal((BigInteger) response.getReturnObject().get(0),true);
        // get Result again
        Tuple4<BigInteger, BigInteger, List<String>, List<BigInteger>> tuple4 = committee.getCommitteeInfo();
        Console.log(JSONUtil.parseObj(tuple4).toStringPretty());
        Assertions.assertEquals(4, tuple4.getValue3().size());

        // creat proposal
         tr = committeeManager.createUpdateGovernorProposal(u4.getAddress(), BigInteger.valueOf(1), blockNumberInterval);
         response = decoder.decodeReceiptWithValues(CommitteeManager.ABI, CommitteeManager.FUNC_CREATEUPDATEGOVERNORPROPOSAL, tr);
        Console.log(JSONUtil.parseObj(response).toStringPretty());
        // vote
        committeeManager.voteProposal((BigInteger) response.getReturnObject().get(0),true);
        // get Result again
        tuple4 = committee.getCommitteeInfo();
        Console.log(JSONUtil.parseObj(tuple4).toStringPretty());
        Assertions.assertEquals(4, tuple4.getValue3().size());
    }

    @Test
    @Order(20)
    public void testSetRate() throws ABICodecException, TransactionException, IOException, ContractException {
        TransactionReceipt tr = committeeManager.createSetRateProposal(BigInteger.valueOf(1), BigInteger.valueOf(2), blockNumberInterval);
        TransactionResponse response = decoder.decodeReceiptWithValues(CommitteeManager.ABI, CommitteeManager.FUNC_CREATESETRATEPROPOSAL, tr);
        Console.log(JSONUtil.parseObj(response).toStringPretty());
        // vote
        committeeManager.voteProposal((BigInteger) response.getReturnObject().get(0),true);
        // get Result again
        Tuple4<BigInteger, BigInteger, List<String>, List<BigInteger>> tuple4 = committee.getCommitteeInfo();
        Console.log(JSONUtil.parseObj(tuple4).toStringPretty());
        Assertions.assertEquals(1, tuple4.getValue1().longValue());
    }

    @Test
    @Order(30)
    public  void testSetAuthType() throws ContractException, ABICodecException, TransactionException, IOException {
        Console.log(deployAuthManager._deployAuthType());

        TransactionReceipt tr = committeeManager.createSetDeployAuthTypeProposal(BigInteger.valueOf(2), blockNumberInterval);
        TransactionResponse response = decoder.decodeReceiptWithValues(CommitteeManager.ABI, CommitteeManager.FUNC_CREATESETRATEPROPOSAL, tr);
        Console.log(JSONUtil.parseObj(response).toStringPretty());
        // vote
        committeeManager.voteProposal((BigInteger) response.getReturnObject().get(0),true);
        // get Result again
        Console.log(deployAuthManager._deployAuthType());
        Assertions.assertEquals(2, deployAuthManager._deployAuthType().longValue());

        // close auth
        committeeManager.createModifyDeployAuthProposal(u5.getAddress(), false, blockNumberInterval);
         tr = committeeManager.createSetDeployAuthTypeProposal(BigInteger.valueOf(1), blockNumberInterval);
         response = decoder.decodeReceiptWithValues(CommitteeManager.ABI, CommitteeManager.FUNC_CREATESETRATEPROPOSAL, tr);
        Console.log(JSONUtil.parseObj(response).toStringPretty());
        // vote
        committeeManager.voteProposal((BigInteger) response.getReturnObject().get(0),true);
        Console.log(deployAuthManager.hasDeployAuth(u5.getAddress()));
        Assertions.assertEquals(false, deployAuthManager.hasDeployAuth(u5.getAddress()));
    }

    @Test
    @Order(40)
    public void testResetAdmin() throws ABICodecException, TransactionException, IOException, ContractException {
        String addr = "0xdcd6dc361724ceb2c40256f5e47582f3b84be849";
        TransactionReceipt tr = committeeManager.deployMethodAuthMgrContract(addr, u5.getAddress());
        TransactionResponse response = decoder.decodeReceiptWithValues(CommitteeManager.ABI, CommitteeManager.FUNC_DEPLOYMETHODAUTHMGRCONTRACT, tr);
        Console.log(JSONUtil.parseObj(response).toStringPretty());
        String methodAuthManagerAddress = (String) response.getReturnObject().get(0);
        Console.log("method auth manager address is {}", methodAuthManagerAddress);
        MethodAuthManager methodAuthManager = MethodAuthManager.load(methodAuthManagerAddress, client, u1);
        Console.log(methodAuthManager._admin());
        Assertions.assertEquals(u5.getAddress(), methodAuthManager._admin());

        tr =committeeManager.createResetAdminProposal(u4.getAddress(),methodAuthManagerAddress, blockNumberInterval);
        response = decoder.decodeReceiptWithValues(CommitteeManager.ABI, CommitteeManager.FUNC_CREATERESETADMINPROPOSAL, tr);
        Console.log(JSONUtil.parseObj(response).toStringPretty());


        // vote
        committeeManager.voteProposal((BigInteger) response.getReturnObject().get(0),true);
        Assertions.assertEquals(u4.getAddress(), methodAuthManager._admin());
    }

}
