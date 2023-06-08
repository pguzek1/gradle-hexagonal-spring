package pl.alfn.project.core.services.util

import com.fasterxml.uuid.Generators.timeBasedEpochGenerator
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator

object Generators {
    val uuid: TimeBasedEpochGenerator = timeBasedEpochGenerator()
}