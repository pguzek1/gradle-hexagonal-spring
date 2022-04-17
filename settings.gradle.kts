rootProject.name = "hexagonal"

// + -------------- +
// |    runnable    |
// + -------------- +
include(":runnable-spring")
project(":runnable-spring").projectDir = file("runnable-spring")
project(":runnable-spring").name = "runnable-spring"


// + -------------- +
// |      core      |
// + -------------- +
include(":application-core-domain")
project(":application-core-domain").projectDir = file("application/core/domain")
project(":application-core-domain").name = "core-domain"

include(":application-core-services")
project(":application-core-services").projectDir = file("application/core/services")
project(":application-core-services").name = "core-services"


// + -------------- +
// |     ports      |
// + -------------- +
//TODO: split ports-driven, ports-driving, ports-model
include(":application-ports")
project(":application-ports").projectDir = file("application/ports")
project(":application-ports").name = "application-ports"


// + -------------- +
// |    adapter     |
// + -------------- +
include(":adapter-spring-rest-reactive")
project(":adapter-spring-rest-reactive").projectDir = file("application/adapter/spring-rest-reactive")
project(":adapter-spring-rest-reactive").name = "adapter-spring-rest-reactive"

include(":adapter-memory-persistence-store")
project(":adapter-memory-persistence-store").projectDir = file("application/adapter/memory-persistence-store")
project(":adapter-memory-persistence-store").name = "adapter-memory-persistence-store"
