package com.jcdvorchak.spotify

import org.junit._
import Assert._
import org.springframework.web.client.RestTemplate

@Test
class AppTest {

  @Before
  def setup() = {

  }

  @Test
  def testMain() = {
    App.main(null)
  }

  @Test
  def testRunWithRest() = {
    App.runWithRest("BQBKhYXfd0G_Y8B6PDYoIx3Tk-4-JWEmZUSCdRQTFOQet-GRGcnsDWrUSAClp6lHW6ye1baZuKsz4xRtLzBXIfmD7OOHNbULolhTZqz3o5tDcubp81Db90fnTGZ9qMiZvKuRm63rl-vWHes10IxBniA")


  }

}


