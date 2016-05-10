<h1> Spring Boot Sample with Swagger for Telstra Public SMS API </h1>

The following demo expsoing Telstra SMS API described at https://dev.telstra.com/content/sms-api-0

You need a T.DEV account at the link here to use this API

  https://dev.telstra.com/
  
<h3> Deploy to Bluemix </h3>

<a href="https://bluemix.net/deploy?repository=https://github.com/papicella/TelstraSMSAPIPublic.git" target="_blank"><img src="http://bluemix.net/deploy/button.png" alt="Bluemix button" /></a>

<h3> Deploy to Bluemix Manually </h3>

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
  buildpack: java_buildpack
  path: ./target/TelstraSMSAPIPublic-0.0.1-SNAPSHOT.jar
```

- Deploy as follows

```
$ cf push -f manifest.yml
```

<h3> Access Swagger UI </h3>

```
http://apples-springboot-telstrasms.mybluemix.net/swagger-ui.html
```

<h3> Import Swagger Defintions into API Connect for example </h3>

```
http://apples-springboot-telstrasms.au-syd.mybluemix.net/v2/api-docs?group=telstra
```

<hr />

Pas Apicella [pasapi at au1.ibm.com] is a Bluemix Technical Specialist at IBM Australia