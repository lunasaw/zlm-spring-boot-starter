# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the **zlm-spring-boot-starter** project - a Spring Boot starter for integrating with ZLMediaKit streaming media
servers. It provides complete REST API encapsulation, Hook event handling, cluster management, and multiple load
balancing strategies for Java developers to easily integrate and manage ZLMediaKit streaming media servers.

## System Requirements

- Java 17+
- Spring Boot 3.5.3+
- ZLMediaKit server
- Jakarta EE specification (uses jakarta packages, not javax packages)

## Common Development Commands

### Build Commands

**Main Project:**

```bash
# Clean and build entire project
mvn clean install

# Run the main application (for development)
mvn spring-boot:run

# Package without running tests
mvn clean package -DskipTests

# Run with specific profile
mvn spring-boot:run -Dspring.profiles.active=dev
```

**Test Module (zlm-spring-boot-starter-test):**

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=NodeServiceTest

# Run tests with coverage
mvn test jacoco:report

# Integration tests
mvn test -Dtest=ZlmApiIntegrationTest
```

### Code Style Commands

```bash
# Format code using ali-code-style.xml
# Import ali-code-style.xml in Eclipse or use Eclipse Code Formatter plugin in IntelliJ

# Validate code style (if using checkstyle plugin)
mvn checkstyle:check
```

## Project Architecture

### Module Structure

The project follows a typical Spring Boot starter structure:

```
zlm-spring-boot-starter/
├── src/main/java/io/github/lunasaw/zlm/
│   ├── api/                    # REST API services and controllers
│   ├── config/                 # Auto-configuration and properties
│   ├── constant/              # API constants and enums
│   ├── entity/                # Data models and DTOs
│   ├── hook/                  # Hook event handling (controller, params, services)
│   ├── node/                  # Load balancing and node management
│   └── ZlmApplication.java    # Main application class
├── src/main/resources/
│   ├── META-INF/spring/       # Auto-configuration imports
│   └── application*.yml       # Configuration files
└── zlm-spring-boot-starter-test/  # Test module
```

### Core Components

**1. Auto-Configuration (`ZlmAutoConfiguration`)**

- Enables conditional bean creation based on `zlm.enable` property
- Auto-configures LoadBalancer, NodeSupplier, and ZlmHookService beans
- Supports 5 load balancing algorithms: random, round_robin, consistent_hashing, weight_round_robin, weight_random

**2. Node Management System**

- **NodeSupplier**: Interface for providing ZLM nodes (supports static config and dynamic discovery)
- **LoadBalancer**: Interface for load balancing algorithms
- **NodeService**: Unified service for node selection and management

**3. Hook Event System**

- **ZlmHookService**: Interface for handling ZLMediaKit callbacks
- **AbstractZlmHookService**: Base implementation with sensible defaults
- **ZlmHookController**: REST controller that receives hook callbacks

**4. REST API Integration**

- **ZlmRestService**: Static methods for calling ZLMediaKit REST APIs
- **ZlmApiController**: REST controller exposing ZLM APIs through HTTP endpoints

### Key Design Patterns

**Auto-Configuration Pattern**: Spring Boot starter pattern with conditional bean creation
**Strategy Pattern**: Multiple load balancing implementations
**Template Method Pattern**: AbstractZlmHookService provides default implementations
**Supplier Pattern**: NodeSupplier for flexible node discovery
**Service Layer Pattern**: NodeService abstracts load balancing complexity

## Configuration

### Basic Configuration

```yaml
zlm:
  enable: true                    # Enable/disable ZLM functionality
  balance: round_robin           # Load balancing algorithm
  nodes:                         # Static node configuration
    - server-id: zlm-node-1
      host: "http://127.0.0.1:9092"
      secret: zlm
      enabled: true
      hook-enabled: true
      weight: 1                  # For weighted algorithms
```

### Dynamic Node Discovery

Implement `NodeSupplier` interface for dynamic node discovery from databases, registries, or config centers:

```java
@Component
public class DatabaseNodeSupplier implements NodeSupplier {
    @Override
    public List<ZlmNode> getNodes() {
        // Return dynamic node list
    }
}
```

## Testing Strategy

### Test Module Structure

Tests are located in the separate `zlm-spring-boot-starter-test` module to avoid circular dependencies.

**Test Categories:**

- **Unit Tests**: `ZlmServiceUnitTest`, `ZlmHookParamTest`
- **Integration Tests**: `ZlmApiIntegrationTest`, `ZlmAutoConfigurationTest`
- **Service Tests**: `NodeServiceTest`, `ZlmApiControllerTest`

**Test Configuration:**

- Uses Spring Boot Test with `@SpringBootTest(classes = ZlmAutoConfiguration.class)`
- JUnit 4 with SpringRunner
- Mockito for mocking dependencies

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test category
mvn test -Dtest="*UnitTest"
mvn test -Dtest="*IntegrationTest"

# Run tests with specific profile
mvn test -Dspring.profiles.active=test
```

## Key APIs and Usage Patterns

### Node Management (NodeService)

```java

@Autowired
private NodeService nodeService;

// Get specific node
ZlmNode node = nodeService.getNode("node-id");

// Load balanced node selection
ZlmNode node = nodeService.selectNode("business-key");

// Get all available nodes
List<ZlmNode> nodes = nodeService.getAllNodes();
```

### Hook Event Handling

```java
@Service
public class CustomZlmHookService extends AbstractZlmHookService {
    @Override
    public HookResult onPlay(OnPlayHookParam param, HttpServletRequest request) {
        // Implement play authorization logic
        return HookResult.SUCCESS();
    }
    
    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param, HttpServletRequest request) {
        // Implement publish authorization logic
        return HookResultForOnPublish.SUCCESS();
    }
}
```

### REST API Usage

```java
// Direct API calls
ServerResponse<Version> version = ZlmRestService.getVersion(host, secret);

// Through controller endpoints
GET /zlm/api/version
POST /zlm/api/media/list
```

## Development Standards

### Code Style

- Use `ali-code-style.xml` for code formatting
- Follow P3C plugin specifications where applicable
- Use TAB indentation for XML files
- Complete and clear comments required
- Format code before committing

### Package and Import Standards

- Use `jakarta.*` packages (Spring Boot 3.x requirement)
- Follow consistent package naming: `io.github.lunasaw.zlm.*`
- Use Lombok annotations (`@Slf4j`, `@Data`, etc.)
- Use Spring Boot annotations (`@Autowired`, `@Service`, `@Component`)

### Configuration Standards

- Support both static (application.yml) and dynamic (NodeSupplier) configuration
- Use `@ConditionalOnProperty` for optional features
- Provide sensible defaults in AbstractZlmHookService
- Use `@ConfigurationProperties` for complex configuration

## Common Integration Patterns

### Spring Boot Application Integration

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// Custom hook service
@Service
public class MyZlmHookService extends AbstractZlmHookService {
    // Override specific hook methods
}

// Custom node supplier for dynamic discovery
@Component
public class MyNodeSupplier implements NodeSupplier {
    // Implement dynamic node discovery
}
```

### API Documentation

The project includes OpenAPI/Swagger integration:

- Access API documentation at: `http://localhost:8080/swagger-ui.html`
- API endpoints are prefixed with `/zlm/api/`
- All endpoints include OpenAPI annotations for documentation

## Important Notes

1. **Version Compatibility**: Ensure ZLMediaKit version compatibility with starter version
2. **Hook Security**: Implement appropriate security measures for Hook interfaces in production
3. **Performance**: Configure connection pools and timeouts for high concurrency
4. **NodeSupplier Performance**: Ensure `getNodes()` method performance as it's called frequently
5. **Error Handling**: NodeSupplier should have robust exception handling to prevent load balancing failures
6. **Jakarta EE**: Use `jakarta.*` packages, not `javax.*` for Spring Boot 3.x compatibility

## Load Balancer Types

- `random`: Random node selection
- `round_robin`: Round-robin selection (default)
- `consistent_hashing`: Consistent hashing algorithm
- `weight_round_robin`: Weighted round-robin
- `weight_random`: Weighted random selection