package com.raj.instaclone.sealed

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raj.instaclone.R

sealed class Tab(@StringRes val title: Int, @DrawableRes val icon: Int){
    object Posts: Tab(title = R.string.posts, icon = R.drawable.posts_grid)
    object Tags: Tab(title = R.string.tags, icon = R.drawable.profile_tags)
}
