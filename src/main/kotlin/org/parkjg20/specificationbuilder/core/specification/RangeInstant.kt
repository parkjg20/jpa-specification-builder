package org.parkjg20.specificationbuilder.core.specification

import java.time.Instant

data class RangeInstant(
    override val start: Instant?,
    override val end: Instant?,
    override val containsEqual: Boolean = false
): AbstractRange<Instant>(start, end, containsEqual) {}