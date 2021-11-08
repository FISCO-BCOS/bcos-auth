package com.webank.fb.auth.fbauthsdk.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionEncoder;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class DeployAuthManager extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50604051602080610be583398101806040528101908080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061008b816100ac640100000000026401000000009004565b60008060146101000a81548160ff021916908360ff16021790555050610222565b6100c43361017b640100000000026401000000009004565b1515610138576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156101ba576001905061021d565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610218576001905061021d565b600090505b919050565b6109b4806102316000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806313af40351461009357806356bd7084146100d65780636154809914610119578063630577e51461015c578063b2bdfa7b146101b7578063bb0aa40c1461020e578063cd2ba0551461023e578063cd5d21181461026f575b600080fd5b34801561009f57600080fd5b506100d4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506102ca565b005b3480156100e257600080fd5b50610117600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061038a565b005b34801561012557600080fd5b5061015a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104f7565b005b34801561016857600080fd5b5061019d600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610663565b604051808215151515815260200191505060405180910390f35b3480156101c357600080fd5b506101cc610785565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561021a57600080fd5b5061023c600480360381019080803560ff1690602001909291905050506107aa565b005b34801561024a57600080fd5b506102536108ce565b604051808260ff1660ff16815260200191505060405180910390f35b34801561027b57600080fd5b506102b0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506108e1565b604051808215151515815260200191505060405180910390f35b6102d3336108e1565b1515610347576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b610393336108e1565b1515610407576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b6001600060149054906101000a900460ff1660ff16141561047f576000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055506104f4565b6002600060149054906101000a900460ff1660ff1614156104f3576001600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b50565b610500336108e1565b1515610574576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b6001600060149054906101000a900460ff1660ff1614156105eb5760018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550610660565b6002600060149054906101000a900460ff1660ff16141561065f576000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b50565b600080600060149054906101000a900460ff1660ff1614156106885760019050610780565b6001600060149054906101000a900460ff1660ff161480156106f35750600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff165b156107015760019050610780565b6002600060149054906101000a900460ff1660ff1614801561076d5750600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16155b1561077b5760019050610780565b600090505b919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6107b3336108e1565b1515610827576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b60018160ff16148061083c575060028160ff16145b15156108b0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f6465706c6f7920617574682074797065206d7573742062652031206f7220322e81525060200191505060405180910390fd5b80600060146101000a81548160ff021916908360ff16021790555050565b600060149054906101000a900460ff1681565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156109205760019050610983565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff16141561097e5760019050610983565b600090505b9190505600a165627a7a72305820bc2522d882424ecda278412cddf3310c876bb409a41ead8b675f68f6698623b90029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50604051602080610be583398101806040528101908080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061008b816100ac640100000000026401000000009004565b60008060146101000a81548160ff021916908360ff16021790555050610222565b6100c43361017b640100000000026401000000009004565b1515610138576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156101ba576001905061021d565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610218576001905061021d565b600090505b919050565b6109b4806102316000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063026a22fd1461009357806305282c70146100d65780631a2052511461011957806328e914891461015c578063291fc90d146101b35780636e0376d4146101e4578063b0ca889b1461023f578063c59065fb1461026f575b600080fd5b34801561009f57600080fd5b506100d4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506102ca565b005b3480156100e257600080fd5b50610117600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610436565b005b34801561012557600080fd5b5061015a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506104f6565b005b34801561016857600080fd5b50610171610663565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156101bf57600080fd5b506101c8610688565b604051808260ff1660ff16815260200191505060405180910390f35b3480156101f057600080fd5b50610225600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061069b565b604051808215151515815260200191505060405180910390f35b34801561024b57600080fd5b5061026d600480360381019080803560ff169060200190929190505050610742565b005b34801561027b57600080fd5b506102b0600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610866565b604051808215151515815260200191505060405180910390f35b6102d33361069b565b1515610347576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b6001600060149054906101000a900460ff1660ff1614156103be5760018060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550610433565b6002600060149054906101000a900460ff1660ff161415610432576000600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b50565b61043f3361069b565b15156104b3576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6104ff3361069b565b1515610573576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b6001600060149054906101000a900460ff1660ff1614156105eb576000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550610660565b6002600060149054906101000a900460ff1660ff16141561065f576001600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b50565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600060149054906101000a900460ff1681565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156106da576001905061073d565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610738576001905061073d565b600090505b919050565b61074b3361069b565b15156107bf576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b60018160ff1614806107d4575060028160ff16145b1515610848576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260208152602001807f6465706c6f7920617574682074797065206d7573742062652031206f7220322e81525060200191505060405180910390fd5b80600060146101000a81548160ff021916908360ff16021790555050565b600080600060149054906101000a900460ff1660ff16141561088b5760019050610983565b6001600060149054906101000a900460ff1660ff161480156108f65750600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff165b156109045760019050610983565b6002600060149054906101000a900460ff1660ff161480156109705750600260008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16155b1561097e5760019050610983565b600090505b9190505600a165627a7a72305820d2ec51f934f2c56f10eda5b6eb4e684de4f6061bb24c77e79f93bc2200f9a39a0029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"closeDeployAuth\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"openDeployAuth\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"hasDeployAuth\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"deployAuthType\",\"type\":\"uint8\"}],\"name\":\"setDeployAuthType\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_deployAuthType\",\"outputs\":[{\"name\":\"\",\"type\":\"uint8\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"src\",\"type\":\"address\"}],\"name\":\"auth\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_SETOWNER = "setOwner";

    public static final String FUNC_CLOSEDEPLOYAUTH = "closeDeployAuth";

    public static final String FUNC_OPENDEPLOYAUTH = "openDeployAuth";

    public static final String FUNC_HASDEPLOYAUTH = "hasDeployAuth";

    public static final String FUNC__OWNER = "_owner";

    public static final String FUNC_SETDEPLOYAUTHTYPE = "setDeployAuthType";

    public static final String FUNC__DEPLOYAUTHTYPE = "_deployAuthType";

    public static final String FUNC_AUTH = "auth";

    protected DeployAuthManager(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt setOwner(String owner) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setOwner(String owner, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetOwner(String owner) {
        final Function function = new Function(
                FUNC_SETOWNER, 
                Arrays.<Type>asList(new Address(owner)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getSetOwnerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt closeDeployAuth(String account) {
        final Function function = new Function(
                FUNC_CLOSEDEPLOYAUTH, 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void closeDeployAuth(String account, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CLOSEDEPLOYAUTH, 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCloseDeployAuth(String account) {
        final Function function = new Function(
                FUNC_CLOSEDEPLOYAUTH, 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getCloseDeployAuthInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CLOSEDEPLOYAUTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt openDeployAuth(String account) {
        final Function function = new Function(
                FUNC_OPENDEPLOYAUTH, 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void openDeployAuth(String account, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_OPENDEPLOYAUTH, 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForOpenDeployAuth(String account) {
        final Function function = new Function(
                FUNC_OPENDEPLOYAUTH, 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getOpenDeployAuthInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_OPENDEPLOYAUTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Boolean hasDeployAuth(String account) throws ContractException {
        final Function function = new Function(FUNC_HASDEPLOYAUTH, 
                Arrays.<Type>asList(new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public String _owner() throws ContractException {
        final Function function = new Function(FUNC__OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt setDeployAuthType(BigInteger deployAuthType) {
        final Function function = new Function(
                FUNC_SETDEPLOYAUTHTYPE, 
                Arrays.<Type>asList(new Uint8(deployAuthType)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setDeployAuthType(BigInteger deployAuthType, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETDEPLOYAUTHTYPE, 
                Arrays.<Type>asList(new Uint8(deployAuthType)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetDeployAuthType(BigInteger deployAuthType) {
        final Function function = new Function(
                FUNC_SETDEPLOYAUTHTYPE, 
                Arrays.<Type>asList(new Uint8(deployAuthType)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getSetDeployAuthTypeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETDEPLOYAUTHTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public BigInteger _deployAuthType() throws ContractException {
        final Function function = new Function(FUNC__DEPLOYAUTHTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public Boolean auth(String src) throws ContractException {
        final Function function = new Function(FUNC_AUTH, 
                Arrays.<Type>asList(new Address(src)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public static DeployAuthManager load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new DeployAuthManager(contractAddress, client, credential);
    }

    public static DeployAuthManager deploy(Client client, CryptoKeyPair credential, String owner) throws ContractException {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(owner)));
        return deploy(DeployAuthManager.class, client, credential, getBinary(client.getCryptoSuite()), encodedConstructor);
    }
}
