/*
 * Copyright (c) 2021 Razeware LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 * 
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.android.jetreddit.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raywenderlich.android.jetreddit.R
import com.raywenderlich.android.jetreddit.domain.model.PostModel
import com.raywenderlich.android.jetreddit.domain.model.PostModel.Companion.DEFAULT_POST

@Composable
fun TextPost(post: PostModel) {
  Post(post) {
    TextContent(post.text)
  }
}

@Composable
fun ImagePost(post: PostModel) {
  Post(post) {
    ImageContent(post.image ?: R.drawable.compose_course)
  }
}

@Composable
fun Post(post: PostModel, content: @Composable () -> Unit = {}) {
  //TODO add your code here
}

@Composable
fun Header(post: PostModel) {
  //TODO add your code here
}

@Composable
fun MoreActionsMenu() {
  var expanded by remember { mutableStateOf(false) }
  Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {

    IconButton(onClick = { expanded = true }) {
      Icon(
        imageVector = Icons.Default.MoreVert,
        tint = Color.DarkGray,
        contentDescription = stringResource(id = R.string.more_actions)
      )
    }

    DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      CustomDropdownMenuItem(
        vectorResourceId = R.drawable.ic_baseline_bookmark_24,
        text = stringResource(id = R.string.save)
      )
    }
  }
}

@Composable
fun CustomDropdownMenuItem(
  @DrawableRes vectorResourceId: Int,
  color: Color = Color.Black,
  text: String,
  onClickAction: () -> Unit = {}
) {
  DropdownMenuItem(onClick = onClickAction) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        imageVector = ImageVector.vectorResource(id = vectorResourceId),
        tint = color,
        contentDescription = stringResource(id = R.string.save)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(text = text, fontWeight = FontWeight.Medium, color = color)
    }
  }
}

@Composable
fun Title(text: String) {
  Text(
    text = text,
    maxLines = 3,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    color = MaterialTheme.colors.primaryVariant,
    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
  )
}

@Composable
fun TextContent(text: String) {
  Text(
    modifier = Modifier.padding(
      start = 16.dp,
      end = 16.dp
    ),
    text = text,
    color = Color.Gray,
    fontSize = 12.sp,
    maxLines = 3
  )
}

@Composable
fun ImageContent(image: Int) {
  val imageAsset = ImageBitmap.imageResource(id = image)
  Image(
    bitmap = imageAsset,
    contentDescription = stringResource(id = R.string.post_header_description),
    modifier = Modifier
      .fillMaxWidth()
      .aspectRatio(imageAsset.width.toFloat() / imageAsset.height),
    contentScale = ContentScale.Crop
  )
}

@Composable
fun PostActions(post: PostModel) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(start = 16.dp, end = 16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    VotingAction(text = post.likes, onUpVoteAction = {}, onDownVoteAction = {})
    PostAction(
      vectorResourceId = R.drawable.ic_baseline_comment_24,
      text = post.comments,
      onClickAction = {}
    )
    PostAction(
      vectorResourceId = R.drawable.ic_baseline_share_24,
      text = stringResource(R.string.share),
      onClickAction = {}
    )
    PostAction(
      vectorResourceId = R.drawable.ic_baseline_emoji_events_24,
      text = stringResource(R.string.award),
      onClickAction = {}
    )
  }
}

@Composable
fun VotingAction(
  text: String,
  onUpVoteAction: () -> Unit,
  onDownVoteAction: () -> Unit
) {
  //TODO add your code here
}

@Composable
fun ArrowButton(onClickAction: () -> Unit, arrowResourceId: Int) {
  //TODO add your code here
}

@Composable
fun PostAction(
  @DrawableRes vectorResourceId: Int,
  text: String,
  onClickAction: () -> Unit
) {
  Box(modifier = Modifier.clickable(onClick = onClickAction)) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Icon(
        ImageVector.vectorResource(id = vectorResourceId),
        contentDescription = stringResource(id = R.string.post_action),
        tint = Color.Gray,
        modifier = Modifier.size(20.dp)
      )
      Spacer(modifier = Modifier.width(4.dp))
      Text(text = text, fontWeight = FontWeight.Medium, color = Color.Gray, fontSize = 12.sp)
    }
  }
}

@Preview
@Composable
fun ArrowButtonPreview() {
  ArrowButton({}, R.drawable.ic_baseline_arrow_upward_24)
}

@Preview
@Composable
fun HeaderPreview() {
  Column {
    Header(DEFAULT_POST)
  }
}

@Preview
@Composable
fun VotingActionPreview() {
  VotingAction("555", {}, {})
}

@Preview
@Composable
fun PostPreview() {
  Post(DEFAULT_POST)
}

@Preview
@Composable
fun TextPostPreview() {
  Post(DEFAULT_POST) {
    TextContent(DEFAULT_POST.text)
  }
}

@Preview
@Composable
fun ImagePostPreview() {
  Post(DEFAULT_POST) {
    ImageContent(DEFAULT_POST.image ?: R.drawable.compose_course)
  }
}