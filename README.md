![](https://github.com/FISCO-BCOS/FISCO-BCOS/blob/master/docs/FISCO_BCOS_Logo.svg)

# bcos-auth

bcos-auth是[FISCO BCOS 3.0](https://github.com/FISCO-BCOS/FISCO-BCOS)的权限控制实现，其中CommitteManager.sol以二进制的形式内置在链上，固定地址为0x1005。

## 文档

- [FISCO BCOS 3.0文档](https://fisco-bcos-doc.readthedocs.io/zh_CN/latest)

## 使用说明

### 1. 账户生成

当底层节点部署时，可由部署者配置一个账户地址作为治理委员会的第一个成员，如果用户未配置，则系统随机生成一个。

### 2. 初始化系统合约

在部署节点时，需要部署治理委员会系统合约CommitteeManager，系统地址为固定的合约地址，需底层预留。
CommitteeManager内部会部署提案管理合约和部署权限管理合约。同时需要系统部署拦截器合约ContractInterceptor。

需要注意的是，在节点初始化系统合约时，需要检测其他节点是否已经部署。

### 3. 设置治理委员会

当节点部署完成后，可完成治理委员会的设置，比如添加其他治理委员会成员，设置投票阈值等操作。
此步骤为非必须项，如果不设置，则治理委员会只有一个成员，即管理员模式。

### 4. 设置部署合约权限

当节点部署完成后，可对部署合约的权限进行设置，分为黑名单和白名单模式，并可以添加对应的账户，开通或禁止相关账户的部署合约权限。
此步骤为非必须向，如果不设置，则所有人默认具有部署合约的权限。

### 5. 部署用户合约

当用户具有部署合约权限时，进行用户合约部署，可通过拦截器合约ContractInterceptor中的create接口进行判断。
每部署一个用户合约， 系统会在对应的目录下部署一个对应的MethodAuthManager合约，用于对合约接口访问权限的管理。

需要注意的是：合约内部署合约，同样需要部署对应的MethodAuthManager合约，详情见设计文档。

### 6. 设置合约访问权限

当完成用户合约部署时，默认所有合约接口在系统层面对于所有用户具有访问权限。此时，合约管理员可以对合约接口的权限进行设置，分为白名单和黑名单两种模式。

### 7. 调用合约接口

在调用合约接口时，系统会通过拦截器合约ContractInterceptor中的call或者sendTransaction对接口访问权限进行校验。

## 加入社区

FISCO BCOS开源社区是国内活跃的开源社区，社区长期为机构和个人开发者提供各类支持与帮助。已有来自各行业的数千名技术爱好者在研究和使用FISCO BCOS。如您对FISCO BCOS开源技术及应用感兴趣，欢迎加入社区获得更多支持与帮助。

![](https://raw.githubusercontent.com/FISCO-BCOS/LargeFiles/master/images/QR_image.png)

## License

bcos-ledger的开源协议为Apache License, 详情参考[LICENSE](./LICENSE).
