# s1e2

## Refactor, add Spring Security and Redis

![sotws1e2](https://github.com/user-attachments/assets/c7a8bb24-e868-4324-820e-32a9756e2023)

## Prerequisites
- Java 23
- Maven
- Npm


## Quickstart

```bash
git clone https://github.com/stackoftheweek/s1e2.git
cd s1e2/backend
docker compose up -d
```
> Starts Ollama on Docker in the background
> http://localhost:11434
> You will get your shell back

```bash
./mvnw spring-boot:run
```
> You will NOT get your shell back
> http://localhost:8080
> Use ctrl-c to stop

In another shell
```bash
cd ../frontend
npm install
npm run dev
```
> You will NOT get your shell back
> Use ctrl-c to stop

```bash
open http://localhost:5173
```