## Running the Project with Docker

This project provides Dockerfiles for both the backend (Node.js) and the root directory (likely serving the Angular frontend). A `docker-compose.yml` is included to orchestrate both services.

### Requirements
- **Node.js version:** 22.13.1 (as specified in the Dockerfiles)
- **Docker Compose:** Ensure you have Docker and Docker Compose installed

### Services and Ports
- **js-backend**
  - Context: `./backend`
  - Dockerfile: `../backendDockerfile`
  - Exposes: `3000` (mapped to host `3000`)
- **ts-root**
  - Context: `./`
  - Dockerfile: `Dockerfile`
  - No port exposed by default (likely serves the frontend; configure as needed)

### Environment Variables
- No required environment variables are set by default. If you need to provide custom environment variables, uncomment the `env_file` lines in the `docker-compose.yml` and create the appropriate `.env` files in the root or backend directories.

### Build and Run Instructions
1. **Build and start the services:**
   ```sh
   docker compose up --build
   ```
   This will build both the backend and frontend images and start the containers.

2. **Access the backend API:**
   - The backend service will be available at `http://localhost:3000`

3. **Frontend service:**
   - If the frontend serves a web app, refer to its documentation for the exposed port or configure as needed in the Dockerfile and compose file.

### Special Configuration
- Both services run as a non-root user for improved security.
- Dependencies are installed using `npm ci` for deterministic builds.
- The backend service exposes data via port `3000`.
- If you need to connect the frontend to the backend, ensure API URLs are set to `http://js-backend:3000` within your frontend configuration.

---

*For further details on service configuration or extending the Docker setup, refer to the provided Dockerfiles and `docker-compose.yml`.*
