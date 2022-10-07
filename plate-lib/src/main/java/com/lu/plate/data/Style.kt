package com.lu.plate.data

import androidx.annotation.Keep
import com.lu.plate.data.drawable.ShapeBackground
import com.lu.plate.util.resolution.StyleResolve

@Keep
class Style(
    var width: String? = null,
    var height: String? = null,
    var marginLeft: String? = null,
    var marginTop: String? = null,
    var marginRight: String? = null,
    var marginBottom: String? = null,
    var paddingLeft: String? = null,
    var paddingTop: String? = null,
    var paddingRight: String? = null,
    var paddingBottom: String? = null,
    var background: ShapeBackground? = null
) : ArchiveSource<Style.Archive>() {


    class Archive(
        var width: Int? = null,
        var height: Int? = null,
        var marginLeft: Int? = null,
        var marginTop: Int? = null,
        var marginRight: Int? = null,
        var marginBottom: Int? = null,
        var paddingLeft: Int? = null,
        var paddingTop: Int? = null,
        var paddingRight: Int? = null,
        var paddingBottom: Int? = null,
        var background: ShapeBackground? = null
    )

    override fun onLazyInitArchiveValue(): Archive {
        return Archive(
            width = StyleResolve.size.parseIntOrNull(width),
            height = StyleResolve.size.parseIntOrNull(height),
            marginLeft = StyleResolve.size.parseIntOrNull(marginLeft),
            marginTop = StyleResolve.size.parseIntOrNull(marginTop),
            marginRight = StyleResolve.size.parseIntOrNull(marginRight),
            marginBottom = StyleResolve.size.parseIntOrNull(marginBottom),
            paddingLeft = StyleResolve.size.parseIntOrNull(paddingLeft),
            paddingTop = StyleResolve.size.parseIntOrNull(paddingTop),
            paddingRight = StyleResolve.size.parseIntOrNull(paddingRight),
            paddingBottom = StyleResolve.size.parseIntOrNull(paddingBottom),
            background = background
        )
    }
}