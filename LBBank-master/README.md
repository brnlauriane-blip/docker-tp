## Running the Project with Docker

This project is containerized using Docker and can be built and run using Docker Compose. Below are the specific instructions and requirements for this setup:

### Project-Specific Docker Requirements
- **Java Version:** Uses Eclipse Temurin JDK 17 for building and JRE 17 for running the application (as specified in the Dockerfile).
- **Build Tool:** Maven Wrapper (`mvnw`) is used for building the project inside the container.
- **Dependencies:** All dependencies are managed via Maven and downloaded during the build stage.

### Environment Variables
- **JAVA_OPTS:** The container sets `JAVA_OPTS` to `-XX:MaxRAMPercentage=80.0` for JVM memory management. No other environment variables are required by default.
- **Optional:** If you need to set additional environment variables, you can use an `.env` file and uncomment the `env_file` line in the `docker-compose.yml`.

### Build and Run Instructions
1. **Build and Start the Application:**
   ```sh
   docker compose up --build
   ```
   This will build the Docker image and start the container for the Java application.

2. **Stopping the Application:**
   ```sh
   docker compose down
   ```

### Special Configuration
- **User Security:** The container runs the application as a non-root user (`appuser`) for improved security.
- **Resources:** The `resources` directory is included in the container for application data.
- **Tests:** Tests are skipped during the Docker build for faster startup.

### Ports
- **No Ports Exposed:** The application does not expose any ports by default. If you need to expose ports, add the `ports:` section to the `docker-compose.yml` and update the Dockerfile as needed.

### Additional Notes
- If you add external services (e.g., databases), update the `docker-compose.yml` to include them and configure networking and volumes accordingly.
- For custom JVM options, modify the `JAVA_OPTS` environment variable in the compose file or Dockerfile.

---
*This section provides up-to-date instructions for running the project using Docker. Please refer to the rest of the README for additional project details.*