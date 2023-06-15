package org.parkjg20.specificationbuilder.core.criteria

import org.parkjg20.specificationbuilder.persistence.specification.LikeInterface
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Path

class LikePredicateBuilder<Entity>(override val field: String) : AbstractPredicateBuilder(field, false), LikePredicateBuilderInterface<Entity> {
    override fun build(value: LikeInterface<out CharSequence>): Specification<Entity> {
        return Specification<Entity> { root, query, builder ->
            val path: Path<String> = root.get(field)

            val predicate = builder.like(path, value.toString())
            if (negative) {
                predicate.not()
            } else {
                predicate
            }
        }
    }

}