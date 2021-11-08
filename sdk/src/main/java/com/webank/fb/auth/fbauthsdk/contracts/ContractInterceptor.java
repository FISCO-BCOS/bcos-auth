package com.webank.fb.auth.fbauthsdk.contracts;

import java.util.Arrays;
import org.fisco.bcos.sdk.abi.FunctionEncoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class ContractInterceptor extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5060405160208061050683398101806040528101908080519060200190929190505050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610483806100836000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806352d485621461005c5780636bedbe88146101005780639ed93318146101a4575b600080fd5b34801561006857600080fd5b506100e6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506101ff565b604051808215151515815260200191505060405180910390f35b34801561010c57600080fd5b5061018a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610215565b604051808215151515815260200191505060405180910390f35b3480156101b057600080fd5b506101e5600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061022b565b604051808215151515815260200191505060405180910390f35b600061020c84848461032b565b90509392505050565b600061022284848461032b565b90509392505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663630577e5836040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156102e957600080fd5b505af11580156102fd573d6000803e3d6000fd5b505050506040513d602081101561031357600080fd5b81019080805190602001909291905050509050919050565b6000808490508073ffffffffffffffffffffffffffffffffffffffff16639612028e85856040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180837bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050602060405180830381600087803b15801561041257600080fd5b505af1158015610426573d6000803e3d6000fd5b505050506040513d602081101561043c57600080fd5b810190808051906020019092919050505091505093925050505600a165627a7a72305820bfe04517b8d24e4135a0cbbdfb4abd5158591eb4bb09c703d384f32aab45682e0029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b5060405160208061050683398101806040528101908080519060200190929190505050806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050610483806100836000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806324f8d0361461005c5780634ba0a39d146101005780636ebc0c50146101a4575b600080fd5b34801561006857600080fd5b506100e6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506101ff565b604051808215151515815260200191505060405180910390f35b34801561010c57600080fd5b5061018a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610215565b604051808215151515815260200191505060405180910390f35b3480156101b057600080fd5b506101e5600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061022b565b604051808215151515815260200191505060405180910390f35b600061020c84848461032b565b90509392505050565b600061022284848461032b565b90509392505050565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c59065fb836040518263ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001915050602060405180830381600087803b1580156102e957600080fd5b505af11580156102fd573d6000803e3d6000fd5b505050506040513d602081101561031357600080fd5b81019080805190602001909291905050509050919050565b6000808490508073ffffffffffffffffffffffffffffffffffffffff1663e282c2cf85856040518363ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040180837bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191681526020018273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200192505050602060405180830381600087803b15801561041257600080fd5b505af1158015610426573d6000803e3d6000fd5b505050506040513d602081101561043c57600080fd5b810190808051906020019092919050505091505093925050505600a165627a7a7230582062519ff6d73dda85ab5059506ee43e0aa5fa8d5b736e4804a7b3224ac13dbf8e0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[{\"name\":\"methodAuthMgrAddr\",\"type\":\"address\"},{\"name\":\"methodId\",\"type\":\"bytes4\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"call\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"methodAuthMgrAddr\",\"type\":\"address\"},{\"name\":\"methodId\",\"type\":\"bytes4\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"sendTransaction\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"create\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"deployAuthMgr\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CALL = "call";

    public static final String FUNC_SENDTRANSACTION = "sendTransaction";

    public static final String FUNC_CREATE = "create";

    protected ContractInterceptor(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public Boolean call(String methodAuthMgrAddr, byte[] methodId, String account) throws ContractException {
        final Function function = new Function(FUNC_CALL, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(methodAuthMgrAddr), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes4(methodId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public Boolean sendTransaction(String methodAuthMgrAddr, byte[] methodId, String account) throws ContractException {
        final Function function = new Function(FUNC_SENDTRANSACTION, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(methodAuthMgrAddr), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes4(methodId), 
                new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public Boolean create(String account) throws ContractException {
        final Function function = new Function(FUNC_CREATE, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public static ContractInterceptor load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new ContractInterceptor(contractAddress, client, credential);
    }

    public static ContractInterceptor deploy(Client client, CryptoKeyPair credential, String deployAuthMgr) throws ContractException {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Address(deployAuthMgr)));
        return deploy(ContractInterceptor.class, client, credential, getBinary(client.getCryptoSuite()), encodedConstructor);
    }
}
