# Oracle Database with Docker

This guide provides instructions on how to spin up an Oracle Database container using Docker and access it via SQL*Plus.

---

## Prerequisites
Ensure you have the following installed:
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Basic knowledge of using terminal/command-line interface

---

## Docker Compose File Breakdown

```yaml
version: "3.8"

services:
  oracle-db:
    image: gvenzl/oracle-xe:latest
    container_name: oracle-db
    restart: always
    ports:
      - "1521:1521" # Oracle SQL*Net
      - "5500:5500" # Oracle Enterprise Manager
    environment:
      ORACLE_PASSWORD: root # Change to a strong password
      ORACLE_ALLOW_REMOTE: "true"
      ORACLE_DATABASE: DS_Practicals
      ORACLE_PDB: ORCLPDB1
    volumes:
      - ~/oracle-data:/opt/oracle/oradata # Persistent data directory

volumes:
  oracle-data:
  oracle-config:
```

### Explanation:
- **Services:** Defines the containers to be run.
- **`oracle-db` Service:**
  - Uses the `gvenzl/oracle-xe:latest` image to run Oracle XE.
  - Container is named `oracle-db`.
  - Automatically restarts if stopped (`restart: always`).
  - Exposes ports `1521` (Oracle SQL*Net) and `5500` (Enterprise Manager UI).
  - Defines environment variables:
    - `ORACLE_PASSWORD`: The password for the database.
    - `ORACLE_ALLOW_REMOTE`: Allows remote connections.
    - `ORACLE_DATABASE`: Name of the database.
    - `ORACLE_PDB`: Defines the Pluggable Database (PDB) name.
  - Uses a **persistent volume** (`~/oracle-data`) to store database data.

---

## How to Run the Oracle Database Container

### Step 1: Clone the Repository or Create a `docker-compose.yml`
If you don’t already have a repository, create a `docker-compose.yml` file and copy the contents of the compose file above.

### Step 2: Start the Oracle Database Container
Run the following command:

```sh
docker-compose up -d
```

- The `-d` flag runs the container in detached mode.
- This will pull the `gvenzl/oracle-xe` image (if not already downloaded) and start the container.

### Step 3: Verify if the Container is Running
Use:

```sh
docker ps
```

You should see `oracle-db` in the running containers list.

---

## How to Log into SQL*Plus

### Option 1: Using SQL*Plus Inside the Container
To enter the container and log in using SQL*Plus, run:

```sh
docker exec -it oracle-db sqlplus sys/root@DS_Practicals as sysdba
```

Alternatively, log in as the default `system` user:

```sh
docker exec -it oracle-db sqlplus system/root@DS_Practicals
```

Or else you can log into the docker container, simply using this command 
```sh
docker exec -it oracle-db bash
```
this will open a bash terminal inside the docker container

and after that we can use ```sqlplus``` command to login to the sqlplus ad give the username as system and pass as root

- Replace `root` with your configured password if different.
- `DS_Practicals` is the database name as defined in the environment variables.
- `sys/root@DS_Practicals as sysdba` logs in with SYSDBA privileges.

### Option 2: Connect Using an External SQL*Plus Client
If you have **SQL*Plus** installed on your machine, you can connect using:

```sh
sqlplus system/root@//localhost:1521/DS_Practicals
```

- Ensure port `1521` is open and accessible.
- Replace `root` with your configured password.

---

## Stopping and Removing the Containers

To stop the containers without removing them:
```sh
docker-compose stop
```

To stop and remove the containers completely:
```sh
docker-compose down
```

To remove all volumes (including database data):
```sh
docker-compose down -v
```

---

## Troubleshooting

### Check Logs
If the container fails to start, check the logs with:
```sh
docker logs oracle-db --tail 50 -f
```

### Remove and Recreate Container
If you run into issues, try removing and recreating the container:
```sh
docker-compose down -v
```
Then start again with:
```sh
docker-compose up -d
```

### Check Connection Issues
If you cannot connect, ensure the database is running:
```sh
docker ps
```
And verify that port `1521` is open:
```sh
netstat -tulnp | grep 1521
```

- If the container is up and restarting frquestlyuse this to change the permission to the volume folder
```sh
sudo chmod -R 777 ~/oracle-data
```

---

## Conclusion
You now have an Oracle Database running inside a Docker container with persistent storage. You can connect using SQL*Plus from inside the container or an external client.

For further customization, refer to the official Oracle XE Docker documentation.

Happy coding! 🚀

