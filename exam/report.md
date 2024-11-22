# Final Project

## 클라우드 네이티브 아키텍처(IaaS)

### 클라우드 아키텍처 설계

![alt text](image-27.png)


## 클라우드 네이티브 모델링(Biz)

### Data Modeling/서비스 분리/설계 역량

https://www.msaez.io/#/188538207/storming/shy


## 클라우드 네이티브 개발 MSA (Dev)

###  `SAGA-Choreography`

분산 트랜잭션 처리를 위한 Saga Pattern 적용

데이터의 일관성 및 보상처리 가능

Choreography 방식 적용

![alt text](image-30.png)

#### `내 서비스에서의 compensation`

![alt text](image-29.png)


#### 분산 데이터 프로젝션 - CQRS 

![alt text](image-28.png)

![alt text](image-26.png)


## 클라우드 네이티브 운영 (Ops, PaaS)


### 클라우드 배포 역량

![alt text](image-20.png)

#### 클라우드 배포 - Container 운영

##### azure pipeline 설정으로 CI 테스트

![alt text](image-21.png)

소스 repository에 commit이 발생하면 Pipeline trigger가 잘 작동하는지 테스트

![alt text](image-22.png)

![alt text](image-24.png)

방금 커밋한 `608f385` git hash로 build trigger 됨을 확인함.

![alt text](image-23.png)

빌드 후 ACR에 이미지가 올라간 것 확인

##### Pipeline CD 구성

![alt text](image-25.png)

위에서 생성한 Order-CI pipeline을 가지고 CD 구성 후 release 성공함


### 컨테이너 인프라 설계 및 구성 역량

-------------

##### load balancer 설정

order 서비스의 `service.yaml`에 serviceType을 `LoadBalancer` 설정

![order service.yaml](image-11.png)

```

kubectl expose deploy order --type=LoadBalancer --port=8080 --target-port=8080


```

#### 컨테이너 자동확장 - HPA 

order 서비스의 `deployment.yaml`에 resource spec을 설정한다.

![alt text](image-9.png)

![alt text](image-3.png)



부하 주기 전
![alt text](image-7.png)


``` bash
# 부하 load
root@siege:/# siege -c20 -t40S -v http://20.249.197.200:8080/orders
```

부하 준 후

![alt text](image-6.png)

![alt text](image-12.png)


#### 컨테이너로부터 환경분리 - CofigMap/Secret

ConfigMap으로 log level 설정하기

현재 실행중인 서비스의 로그가 `DEBUG` 레벨임을 확인
![alt text](image-17.png)

`config-dev` configMap 객체에 `ORDER_LOG_LEVEL: INFO`로 설정

```YAML
kubectl apply -f - <<EOF
apiVersion: v1
kind: ConfigMap
metadata:
  name: config-dev
  namespace: default
data:
  ORDER_DB_URL: jdbc:mysql://mysql:3306/connectdb1?serverTimezone=Asia/Seoul&useSSL=false
  ORDER_DB_USER: myuser
  ORDER_DB_PASS: mypass
  ORDER_LOG_LEVEL: INFO
EOF
```

![alt text](image-18.png)

```bash
kubectl logs -l app=config
```

![alt text](image-19.png)

서비스의 로그가 `INFO` 레벨임을 확인함.

#### 클라우드스토리지 활용 - PVC 

#### 셀프 힐링/무정지배포 - Liveness/Rediness Probe 

##### health check용 간단한 method 생성

OrderContoller.java 내 health check용 method 생성
![alt text](image-8.png)

order/kubernetes/deployment.yaml에 readinessProbe, livenessProbe 설정
![alt text](image-14.png)


실행 중인 order 서비스

![alt text](image-13.png)

```bash
# 부하 load 중
siege -c1 -t60S -v http://order:8080/orders --delay=1S
```

실행 중인 order 서비스의 `deployment.yaml`에 image source를 변경한 후 
![alt text](image-15.png)

siege 테스트 결과 확인 -> Availability 100.00 % 확인 가능.

```
HTTP/1.1 200     0.01 secs:     301 bytes ==> GET  /orders
HTTP/1.1 200     0.01 secs:     301 bytes ==> GET  /orders

Lifting the server siege...
Transactions:                    112 hits
Availability:                 100.00 %
Elapsed time:                  59.04 secs
Data transferred:               0.03 MB
Response time:                  0.01 secs
Transaction rate:               1.90 trans/sec
Throughput:                     0.00 MB/sec
Concurrency:                    0.02
Successful transactions:         112
Failed transactions:               0
Longest transaction:            0.02
Shortest transaction:           0.00
```

`kubectl describe deployments.apps order`
![alt text](image-16.png)

order deploy의 image가 서비스 실행 중 변경한 `jinyoung/order:stable` 임을 확인하였다.

무중단 배포에 성공함

#### 서비스 메쉬 응용 - Mesh 

#### 통합 모니터링 - Loggregation/Monitoring





