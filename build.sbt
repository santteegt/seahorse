// Copyright (c) 2015, CodiLime Inc.
//
// Owner: Jacek Laskowski

name := "deepsense-backend"

lazy val deeplang          = project
lazy val experimentmanager = project dependsOn (deeplang, graphjson)
lazy val graph             = project dependsOn deeplang
lazy val graphexecutor     = project dependsOn graph
lazy val graphjson         = project dependsOn (graph, deeplang)

lazy val `sbt-common-settings-plugin` = project in file("sbt-common-settings-plugin")