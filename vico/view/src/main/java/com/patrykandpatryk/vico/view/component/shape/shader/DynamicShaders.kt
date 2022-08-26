/*
 * Copyright 2022 Patryk Goworowski and Patryk Michalik
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package com.patrykandpatryk.vico.view.component.shape.shader

import android.graphics.LinearGradient
import android.graphics.Shader
import com.patrykandpatryk.vico.core.component.Component
import com.patrykandpatryk.vico.core.component.shape.shader.CacheableDynamicShader
import com.patrykandpatryk.vico.core.component.shape.shader.ComponentShader
import com.patrykandpatryk.vico.core.component.shape.shader.DynamicShader
import com.patrykandpatryk.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatryk.vico.core.context.DrawContext

/**
 * Creates a [ComponentShader] out of given [component].
 *
 * @param component used as a pattern in the [Shader].
 * @param componentSizeDp the size of the [component] in the dp unit.
 * @param checkeredArrangement whether the [component] will be arranged in a checkered pattern.
 * @param tileXMode the horizontal tiling mode for the [component].
 * @param tileYMode the vertical tiling mode for the [component].
 */
public fun DynamicShaders.fromComponent(
    component: Component,
    componentSizeDp: Float,
    checkeredArrangement: Boolean = true,
    tileXMode: Shader.TileMode = Shader.TileMode.REPEAT,
    tileYMode: Shader.TileMode = tileXMode,
): ComponentShader = ComponentShader(
    component = component,
    componentSizeDp = componentSizeDp,
    checkeredArrangement = checkeredArrangement,
    tileXMode = tileXMode,
    tileYMode = tileYMode,
)

/**
 * Creates a [DynamicShader] in the form of a horizontal gradient.
 *
 * @param colors the sRGB colors to be distributed along the gradient line.
 */
public fun DynamicShaders.horizontalGradient(
    vararg colors: Int,
): DynamicShader = horizontalGradient(colors)

/**
 * Creates a [DynamicShader] in the form of a horizontal gradient.
 *
 * @param colors the sRGB colors to be distributed along the gradient line.
 * @param positions the relative positions ([0..1]) of each corresponding color in the color array.
 * This may be null, in which case the colors will be distributed evenly along the gradient line.
 */
public fun DynamicShaders.horizontalGradient(
    colors: IntArray,
    positions: FloatArray? = null,
): DynamicShader = object : CacheableDynamicShader() {

    override fun createShader(
        context: DrawContext,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ): Shader =
        LinearGradient(
            left,
            top,
            right,
            top,
            colors,
            positions,
            Shader.TileMode.CLAMP,
        )

    override fun createKey(left: Float, top: Float, right: Float, bottom: Float): String =
        "%s,%s".format(left, right)
}

/**
 * Creates a [DynamicShader] in the form of a vertical gradient.
 *
 * @param colors the sRGB colors to be distributed along the gradient line.
 */
public fun DynamicShaders.verticalGradient(
    vararg colors: Int,
): DynamicShader = verticalGradient(colors)

/**
 * Creates a [DynamicShader] in the form of a vertical gradient.
 *
 * @param colors the sRGB colors to be distributed along the gradient line.
 * @param positions the relative positions ([0..1]) of each corresponding color in the color array.
 * This may be null, in which case the colors will be distributed evenly along the gradient line.
 */
public fun DynamicShaders.verticalGradient(
    colors: IntArray,
    positions: FloatArray? = null,
): DynamicShader = object : CacheableDynamicShader() {

    override fun createShader(
        context: DrawContext,
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
    ): Shader =
        LinearGradient(
            left,
            top,
            left,
            bottom,
            colors,
            positions,
            Shader.TileMode.CLAMP,
        )

    override fun createKey(left: Float, top: Float, right: Float, bottom: Float): String =
        "%s,%s".format(top, bottom)
}
