package com.lu.plate.data.drawable

import org.w3c.dom.Attr

class ShapeBackground(
    var shape: String = "rectangle",
    //dimension
    var innerRadius: String? = null,
    var innerRadiusRatio: Float? = null,
    var dither: Boolean? = null,
    //dimension
    var thickness: String? = null,
    var thicknessRatio: Float? = null,
    //color
    var tint: String? = null,
    //tintMode attrs
    var tintMode: String? = null,
    var useLevel: Boolean? = null,
    var visible: Boolean? = null,

    var solid: Solid? = null,
    var gradient: Gradient? = null,
    var stroke: Stroke? = null,
    var corners: Corners? = null,
    var size: Size? = null,
    var padding: Padding? = null

) {
    class Solid(var color: String)

    class Gradient(
        var startColor: String? = null,
        var centerColor: String? = null,
        var endColor: String? = null,
        var useLevel: Boolean? = null,
        var angle: Float? = null,
        var type: String? = null,
        var centerX: Float? = null,
        var centerY: Float? = null
    )

    class Stroke(
        var width: String? = null,
        var color: String? = null,
        var dashWidth: String? = null,
        var dashGap: String? = null
    )

    class Corners(
        var radius: String? = null,
        var topLeftRadius: String? = null,
        var topRightRadius: String? = null,
        var bottomLeftRadius: String? = null,
        var bottomRightRadius: String? = null
    )

    class Size(
        var width: String? = null,
        var height: String? = null
    )

    class Padding(
        var left: String? = null,
        var top: String? = null,
        var right: String? = null,
        var bottom: String? = null
    )
}
