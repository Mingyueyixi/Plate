package com.lu.plate.data

import androidx.annotation.Keep
import com.lu.plate.data.drawable.ShapeBackground
import com.lu.plate.util.resolve.StyleComposite

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
            width = StyleComposite.size.parseIntOrNull(width),
            height = StyleComposite.size.parseIntOrNull(height),
            marginLeft = StyleComposite.size.parseIntOrNull(marginLeft),
            marginTop = StyleComposite.size.parseIntOrNull(marginTop),
            marginRight = StyleComposite.size.parseIntOrNull(marginRight),
            marginBottom = StyleComposite.size.parseIntOrNull(marginBottom),
            paddingLeft = StyleComposite.size.parseIntOrNull(paddingLeft),
            paddingTop = StyleComposite.size.parseIntOrNull(paddingTop),
            paddingRight = StyleComposite.size.parseIntOrNull(paddingRight),
            paddingBottom = StyleComposite.size.parseIntOrNull(paddingBottom),
            background = background
        )
    }
}