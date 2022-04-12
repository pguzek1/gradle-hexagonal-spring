rootProject.name = "hexagonal"
rootProject.projectDir = file("./")

include(":application:core:domain")
findProject(":application:core:domain")?.name = "domain"

include(":application:ports")
findProject(":application:core:domain")?.name = "domain"
include("application:core:services")
findProject(":application:core:services")?.name = "services"
