/*
 * Copyright 2012-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.facebook.buck.cli;

import com.facebook.buck.rules.JavaPackageFinder;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;

import org.kohsuke.args4j.Option;

import java.util.List;

public class ProjectCommandOptions extends AbstractCommandOptions {

  @Option(name = "--target", usage = "Only supported value is 'intellij'")
  private String target;

  ProjectCommandOptions(BuckConfig buckConfig) {
    super(buckConfig);
  }

  public String getTarget() {
    return target;
  }

  public ImmutableMap<String, String> getBasePathToAliasMap() {
    return getBuckConfig().getBasePathToAliasMap();
  }

  public JavaPackageFinder getJavaPackageFinder() {
    return getBuckConfig().createDefaultJavaPackageFinder();
  }

  public Optional<String> getPathToDefaultAndroidManifest() {
    return getBuckConfig().getPathToDefaultAndroidManifest();
  }

  private List<String> getInitialTargets() {
    return getBuckConfig().getInitialTargets();
  }

  public boolean hasInitialTargets() {
    return !getInitialTargets().isEmpty();
  }

  public BuildCommandOptions createBuildCommandOptionsWithInitialTargets() {
    BuildCommandOptions buildCommandOptions = new BuildCommandOptions(getBuckConfig());
    buildCommandOptions.setArguments(getInitialTargets());
    return buildCommandOptions;
  }
}
