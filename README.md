<h1>Spring Boot Sample with Swagger for Telstra Public SMS API</h1>

The following demo expsoing Telstra SMS API described at https://dev.telstra.com/content/sms-api-0

You need a T.DEV account at the link here to use this API

  https://dev.telstra.com/
  
<h3> Deploy to Pivotal Cloud Foundry </h3>

- Clone project as follows

```
git clone https://github.com/papicella/TelstraSMSAPIPublic.git
```

- Package as follows [This may take a while if downloaded maven repos is required]

```
$ cd TelstraSMSAPIPublic
$ mvn package
```

- Edit manifest.yml to ensure HOST is unique

```
applications:
- name: springboot-telstrasms
  memory: 512M
  instances: 1
  host: apples-springboot-telstrasms
  path: ./target/TelstraSMSAPIPublic-0.0.1-SNAPSHOT.jar
```

- Deploy as follows, once you have a unique HOSTNAME Route to use

```
$ cf push -f manifest.yml
```

Once deployed it will look as follows in Pivotal Apps manager

![alt tag](https://dl.dropboxusercontent.com/u/15829935/platform-demos/images/tel-sms-api-1.png)

<h3> Access Swagger UI </h3>

Note: Make sure you use the correct HOSTNAME route you used in manifest.yml

```
http://apples-springboot-telstrasms.pcfdemo.net/swagger-ui.html
```

Eg:

![alt tag](https://dl.dropboxusercontent.com/u/15829935/platform-demos/images/springboot-swagger.png)

<h3> Import Swagger Defintions for API Consumption </h3>

```
http://apples-springboot-telstrasms.pcfdemo.net/v2/api-docs?group=telstra
```

<hr />

Pas Apicella [papicella at pivotal.io] is a Senior Platform Architect at Pivotal Australia