package com.visitscotland.demo.beans;
/*
 * Copyright 2014-2024 Bloomreach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "playground:contentdocument")
@Node(jcrType = "playground:contentdocument")
public class ContentDocument extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "playground:introduction")
    public String getIntroduction() {
        return getSingleProperty("playground:introduction");
    }

    @HippoEssentialsGenerated(internalName = "playground:title")
    public String getTitle() {
        return getSingleProperty("playground:title");
    }

    @HippoEssentialsGenerated(internalName = "playground:content")
    public HippoHtml getContent() {
        return getHippoHtml("playground:content");
    }

    @HippoEssentialsGenerated(internalName = "playground:publicationdate")
    public Calendar getPublicationDate() {
        return getSingleProperty("playground:publicationdate");
    }
}
