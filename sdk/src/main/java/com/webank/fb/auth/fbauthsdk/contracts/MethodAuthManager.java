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
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes4;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class MethodAuthManager extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50604051606080611605833981018060405281019080805190602001909291908051906020019092919080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061012181610129640100000000026401000000009004565b50505061029f565b610141336101f8640100000000026401000000009004565b15156101b5576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610237576001905061029a565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610295576001905061029a565b600090505b919050565b611357806102ae6000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806301bc45c9146100a95780631095d1701461010057806313af40351461016c57806337988767146101af578063594019031461020657806365143c05146102725780639612028e146102b5578063a3d21d4414610339578063b2bdfa7b14610392578063cd5d2118146103e9575b600080fd5b3480156100b557600080fd5b506100be610444565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561010c57600080fd5b5061016a60048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061046a565b005b34801561017857600080fd5b506101ad600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061085c565b005b3480156101bb57600080fd5b506101c461091c565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561021257600080fd5b5061027060048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610942565b005b34801561027e57600080fd5b506102b3600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610d34565b005b3480156102c157600080fd5b5061031f60048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610df5565b604051808215151515815260200191505060405180910390f35b34801561034557600080fd5b5061039060048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803560ff1690602001909291905050506110a3565b005b34801561039e57600080fd5b506103a761125f565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156103f557600080fd5b5061042a600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050611284565b604051808215151515815260200191505060405180910390f35b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561052f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f796f75206d7573742062652061646d696e00000000000000000000000000000081525060200191505060405180910390fd5b600060036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff161415151561062f576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260338152602001807f796f752073686f756c642073657420746865206d6574686f642061636365737381526020017f206175746820747970652066697273746c792e0000000000000000000000000081525060400191505060405180910390fd5b600160036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff16141561074557600060046000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550610858565b600260036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff16141561085757600160056000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b5050565b61086533611284565b15156108d9576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610a07576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f796f75206d7573742062652061646d696e00000000000000000000000000000081525060200191505060405180910390fd5b600060036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff1614151515610b07576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260338152602001807f796f752073686f756c642073657420746865206d6574686f642061636365737381526020017f206175746820747970652066697273746c792e0000000000000000000000000081525060400191505060405180910390fd5b600160036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff161415610c1d57600160046000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550610d30565b600260036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff161415610d2f57600060056000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b5050565b610d3d33611284565b1515610db1576040517f08c379a0000000000000","00000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60008060036000857bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff161415610e69576001905061109d565b600160036000857bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff16148015610f72575060046000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff165b15610f80576001905061109d565b600260036000857bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff1614801561108a575060056000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16155b15611098576001905061109d565b600090505b92915050565b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515611168576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f796f75206d7573742062652061646d696e00000000000000000000000000000081525060200191505060405180910390fd5b60018160ff16148061117d575060028160ff16145b15156111f1576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f617574682074797065206d7573742062652031206f7220322e0000000000000081525060200191505060405180910390fd5b8060036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060006101000a81548160ff021916908360ff1602179055505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156112c35760019050611326565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156113215760019050611326565b600090505b9190505600a165627a7a72305820c9e103a1d7ea02c1821d8210f33148ab5ea30bc9908e9b3aee613a412d3b5ef50029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50604051606080611605833981018060405281019080805190602001909291908051906020019092919080519060200190929190505050336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555082600260006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555081600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555061012181610129640100000000026401000000009004565b50505061029f565b610141336101f8640100000000026401000000009004565b15156101b5576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610237576001905061029a565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff161415610295576001905061029a565b600090505b919050565b611357806102ae6000396000f3006080604052600436106100a4576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806305282c70146100a957806328e91489146100ec5780633695cf8814610143578063604d83dc146101865780636e0376d4146101df578063900862271461023a5780639b68af0a146102a6578063b2e6ea8a146102fd578063e282c2cf14610354578063fd57b049146103d8575b600080fd5b3480156100b557600080fd5b506100ea600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610444565b005b3480156100f857600080fd5b50610101610504565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561014f57600080fd5b50610184600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610529565b005b34801561019257600080fd5b506101dd60048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803560ff1690602001909291905050506105ea565b005b3480156101eb57600080fd5b50610220600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506107a6565b604051808215151515815260200191505060405180910390f35b34801561024657600080fd5b506102a460048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061084d565b005b3480156102b257600080fd5b506102bb610c3f565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561030957600080fd5b50610312610c65565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561036057600080fd5b506103be60048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610c8b565b604051808215151515815260200191505060405180910390f35b3480156103e457600080fd5b5061044260048036038101908080357bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19169060200190929190803573ffffffffffffffffffffffffffffffffffffffff169060200190929190505050610f39565b005b61044d336107a6565b15156104c1576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b806000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b610532336107a6565b15156105a6576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252600b8152602001807f4f6e6c79206f776e65722100000000000000000000000000000000000000000081525060200191505060405180910390fd5b80600160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555050565b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415156106af576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f796f75206d7573742062652061646d696e00000000000000000000000000000081525060200191505060405180910390fd5b60018160ff1614806106c4575060028160ff16145b1515610738576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260198152602001807f617574682074797065206d7573742062652031206f7220322e0000000000000081525060200191505060405180910390fd5b8060036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060006101000a81548160ff021916908360ff1602179055505050565b60003073ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156107e55760019050610848565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168273ffffffffffffffffffffffffffffffffffffffff1614156108435760019050610848565b600090505b919050565b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610912576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f796f75206d7573742062652061646d696e00000000000000000000000000000081525060200191505060405180910390fd5b600060036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff1614151515610a12576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260338152602001807f796f752073686f756c642073657420746865206d6574686f642061636365737381526020017f206175746820747970652066697273746c792e0000000000000000000000000081525060400191505060405180910390fd5b600160036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff161415610b2857600160046000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550610c3b565b600260036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff161415610c3a57600060056000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b5050565b600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600260009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060036000857bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff161415610cff5760019050610f33565b600160036000857bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff191681526020019081526020016000","2060009054906101000a900460ff1660ff16148015610e08575060046000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff165b15610e165760019050610f33565b600260036000857bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff16148015610f20575060056000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060009054906101000a900460ff16155b15610f2e5760019050610f33565b600090505b92915050565b3373ffffffffffffffffffffffffffffffffffffffff16600160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610ffe576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260118152602001807f796f75206d7573742062652061646d696e00000000000000000000000000000081525060200191505060405180910390fd5b600060036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff16141515156110fe576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260338152602001807f796f752073686f756c642073657420746865206d6574686f642061636365737381526020017f206175746820747970652066697273746c792e0000000000000000000000000081525060400191505060405180910390fd5b600160036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff16141561121457600060046000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff021916908315150217905550611327565b600260036000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060009054906101000a900460ff1660ff16141561132657600160056000847bffffffffffffffffffffffffffffffffffffffffffffffffffffffff19167bffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060006101000a81548160ff0219169083151502179055505b5b50505600a165627a7a7230582085b3ba6af61bd415ef15b24c5613ad7e8ace286ee52d2506a5a9c03cf742dd700029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":true,\"inputs\":[],\"name\":\"_admin\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"methodId\",\"type\":\"bytes4\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"closeMehtodAccessAuth\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"owner\",\"type\":\"address\"}],\"name\":\"setOwner\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_contractAddress\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"methodId\",\"type\":\"bytes4\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"openMethodAccessAuth\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"admin\",\"type\":\"address\"}],\"name\":\"resetAdmin\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"methodId\",\"type\":\"bytes4\"},{\"name\":\"account\",\"type\":\"address\"}],\"name\":\"hasMethodAccessAuth\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"methodId\",\"type\":\"bytes4\"},{\"name\":\"authType\",\"type\":\"uint8\"}],\"name\":\"setMethodAccessAuthType\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"_owner\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"src\",\"type\":\"address\"}],\"name\":\"auth\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"contractAddress\",\"type\":\"address\"},{\"name\":\"admin\",\"type\":\"address\"},{\"name\":\"owner\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC__ADMIN = "_admin";

    public static final String FUNC_CLOSEMEHTODACCESSAUTH = "closeMehtodAccessAuth";

    public static final String FUNC_SETOWNER = "setOwner";

    public static final String FUNC__CONTRACTADDRESS = "_contractAddress";

    public static final String FUNC_OPENMETHODACCESSAUTH = "openMethodAccessAuth";

    public static final String FUNC_RESETADMIN = "resetAdmin";

    public static final String FUNC_HASMETHODACCESSAUTH = "hasMethodAccessAuth";

    public static final String FUNC_SETMETHODACCESSAUTHTYPE = "setMethodAccessAuthType";

    public static final String FUNC__OWNER = "_owner";

    public static final String FUNC_AUTH = "auth";

    protected MethodAuthManager(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public String _admin() throws ContractException {
        final Function function = new Function(FUNC__ADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt closeMehtodAccessAuth(byte[] methodId, String account) {
        final Function function = new Function(
                FUNC_CLOSEMEHTODACCESSAUTH, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void closeMehtodAccessAuth(byte[] methodId, String account, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CLOSEMEHTODACCESSAUTH, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCloseMehtodAccessAuth(byte[] methodId, String account) {
        final Function function = new Function(
                FUNC_CLOSEMEHTODACCESSAUTH, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<byte[], String> getCloseMehtodAccessAuthInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CLOSEMEHTODACCESSAUTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<byte[], String>(

                (byte[]) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
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

    public String _contractAddress() throws ContractException {
        final Function function = new Function(FUNC__CONTRACTADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt openMethodAccessAuth(byte[] methodId, String account) {
        final Function function = new Function(
                FUNC_OPENMETHODACCESSAUTH, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void openMethodAccessAuth(byte[] methodId, String account, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_OPENMETHODACCESSAUTH, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForOpenMethodAccessAuth(byte[] methodId, String account) {
        final Function function = new Function(
                FUNC_OPENMETHODACCESSAUTH, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<byte[], String> getOpenMethodAccessAuthInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_OPENMETHODACCESSAUTH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}, new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<byte[], String>(

                (byte[]) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public TransactionReceipt resetAdmin(String admin) {
        final Function function = new Function(
                FUNC_RESETADMIN, 
                Arrays.<Type>asList(new Address(admin)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void resetAdmin(String admin, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_RESETADMIN, 
                Arrays.<Type>asList(new Address(admin)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForResetAdmin(String admin) {
        final Function function = new Function(
                FUNC_RESETADMIN, 
                Arrays.<Type>asList(new Address(admin)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<String> getResetAdminInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_RESETADMIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public Boolean hasMethodAccessAuth(byte[] methodId, String account) throws ContractException {
        final Function function = new Function(FUNC_HASMETHODACCESSAUTH, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Address(account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public TransactionReceipt setMethodAccessAuthType(byte[] methodId, BigInteger authType) {
        final Function function = new Function(
                FUNC_SETMETHODACCESSAUTHTYPE, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Uint8(authType)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void setMethodAccessAuthType(byte[] methodId, BigInteger authType, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_SETMETHODACCESSAUTHTYPE, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Uint8(authType)),
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForSetMethodAccessAuthType(byte[] methodId, BigInteger authType) {
        final Function function = new Function(
                FUNC_SETMETHODACCESSAUTHTYPE, 
                Arrays.<Type>asList(new Bytes4(methodId),
                new Uint8(authType)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<byte[], BigInteger> getSetMethodAccessAuthTypeInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SETMETHODACCESSAUTHTYPE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes4>() {}, new TypeReference<Uint8>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<byte[], BigInteger>(

                (byte[]) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public String _owner() throws ContractException {
        final Function function = new Function(FUNC__OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public Boolean auth(String src) throws ContractException {
        final Function function = new Function(FUNC_AUTH, 
                Arrays.<Type>asList(new Address(src)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public static MethodAuthManager load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new MethodAuthManager(contractAddress, client, credential);
    }

    public static MethodAuthManager deploy(Client client, CryptoKeyPair credential, String contractAddress, String admin, String owner) throws ContractException {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(contractAddress),
                new Address(admin),
                new Address(owner)));
        return deploy(MethodAuthManager.class, client, credential, getBinary(client.getCryptoSuite()), encodedConstructor);
    }
}
