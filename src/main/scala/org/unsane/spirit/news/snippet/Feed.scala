/**
 * Copyright (c) 2010 spirit-fhs
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the author nor the names of his contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHORS ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE AUTHORS OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.unsane.spirit.news
package snippet

import net.liftweb.http._
import net.liftweb.http.rest._
import org.unsane.spirit.news.model.Entry
import net.liftweb.textile.TextileParser

object Feed extends RestHelper with SpiritHelpers {

  serve {
    case "feed" :: _ XmlGet _=> createFeed
  }

  lazy val url = S.hostAndPath //"http://spirit.fh-schmalkalden.de"

  /**
   * creates RSS2 feed with latest messages
   * @return XML structure for feed
   */
  def createFeed = {

    val news = Entry.findAll.sortWith(
      (entry1, entry2) => (entry1 > entry2)
    )

    <rss version="2.0">
			<channel>
				<title>Spirit @ FH-Schmalkalden - RSS Feed</title>
				<link>{ url }</link>
				<description>RSS Feed für die aktuellen Meldungen am Fachbereich Informatik</description>
				<language>de-de</language>
				<image>
				  <url>{ url }/spirit_style/images/logo_spirit.png</url>
				  <title>Spirit</title>
				  <link>{ url }</link>
				</image>
				<ttl>60</ttl>
				{ news.map { entry =>
					(<item>
						<title>{ entry.subject.value } ({ semesterChanger(entry.semester.value) })</title>
						<description>{ TextileParser.paraFixer(TextileParser.toHtml(entry.news.value)) }</description>
						<link>{ url}/entry/{entry.nr.value }</link>
						<author>{ entry.writer.value }</author>
						<guid>{ entry.nr.value }</guid>
						<pubDate>{ entry.date.value }</pubDate>
					</item>)
				}}
			</channel>
		</rss>
	}


}
