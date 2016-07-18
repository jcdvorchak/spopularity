package com.jcdvorchak.spotify.stats

import org.junit._

@Test
class StatsTest {

  val stats = new Stats

  @Before
  def setup() = {
  }


  //  Map(ROAM -> (8,41), The Wonder Years -> (1,56), The Starting Line -> (18,48), That Was Something -> (10,9), Aaron West & The Roaring Twenties -> (15,43), Passion Pit -> (9,67), Fireworks -> (16,37), State Champs -> (11,58), Neck Deep -> (12,62), Say Anything -> (14,56), The Lumineers -> (3,78), Forever Came Calling -> (13,34), Modern Baseball -> (2,63), Into It. Over It. -> (4,44), Motion City Soundtrack -> (7,56), Sundressed -> (20,13), Knuckle Puck -> (19,52), Broadside -> (17,43), Real Friends -> (6,57), The Story So Far -> (5,61))
  //  Map(Too Good -> 17, Say It (feat. Tove Lo) -> 33, This One's For You (feat. Zara Larsson) - Official Song UEFA EURO 2016 -> 27, Rock Bottom -> 42, Don't Mind -> 15, Needed Me -> 24, Kill Em With Kindness -> 10, Pity Party -> 39, Once In A While -> 22, Close -> 20, Gold -> 12, All In My Head (Flex) -> 44, Into You -> 28, Sit Still, Look Pretty -> 35, Bang My Head (feat. Sia & Fetty Wap) -> 49, Ride -> 9, Running Out -> 50, Wild Things -> 19, Hymn For The Weekend - Seeb Remix -> 41, Light It Up (feat. Nyla & Fuse ODG) [Remix] -> 14, No Money -> 18, i hate u, i love u (feat. olivia o'brien) -> 8, Stay -> 38, In My Room (feat. Ty Dolla $ign & Tyga) -> 43, Never Forget You -> 16, Panda -> 23, Work from Home -> 5, YOUTH -> 40, One Dance -> 7, Treat You Better -> 11, Fast Car - Radio Edit -> 26, Send My Love (To Your New Lover) -> 37, CAN'T STOP THE FEELING! (Original Song From DreamWorks Animation's "Trolls") -> 3, Please Don't Go -> 6, Sex -> 29, Don't Let Me Down -> 2, Cheap Thrills -> 4, Lush Life -> 32, Roses -> 47, Middle -> 36, If It Ain't Love -> 30, Working For It -> 34, This Is What You Came For -> 1, Never Be Like You (feat. Kai) -> 31, Wherever I Go -> 21, The Ocean -> 46, I Took A Pill In Ibiza - Seeb Remix -> 25, LIKE I WOULD -> 45, Capsize -> 13, No Broken Hearts (feat. Nicki Minaj) -> 48)
  //  Map(Astrid S -> 50, MNEK -> 16, Nicki Minaj -> 48, Wiz Kid -> 7, Jason Derulo -> 30, FRENSHIP -> 13, Kiiara -> 12, Maty Noyes -> 38, Fuse Odg -> 14, Drake -> 7, Kris Kross Amsterdam -> 29, Tyga -> 43, Calvin Harris -> 1, Dakota -> 26, Flume -> 31, Selena Gomez -> 10, DJ Mustard -> 43, THEY. -> 34, Yellow Claw -> 43, Twenty One Pilots -> 9, Justin Timberlake -> 3, Jonas Blue -> 26, Nyla -> 14, Joel Adams -> 6, Tove Lo -> 20, DNCE -> 42, Hailee Steinfeld -> 42, Shawn Mendes -> 11, Ariana Grande -> 28, Nick Jonas -> 20, Olivia O'Brien -> 8, Emily Warren -> 13, Desiigner -> 23, Mike Perry -> 46, Ty Dolla $ign -> 5, Kent Jones -> 15, gnash -> 8, Skrillex -> 34, ZAYN -> 45, Galantis -> 18, Zara Larsson -> 16, Shy Martin -> 46, Fifth Harmony -> 5, Major Lazer -> 14, Alessia Cara -> 19, Melanie Martinez -> 39, ZHU -> 34, Bipolar Sunshine -> 36, Adele -> 37, Coldplay -> 41, Fetty Wap -> 44, OneRepublic -> 21, Daya -> 2, DJ Snake -> 36, Seeb -> 41, Mike Posner -> 25, Kyla -> 7, Troye Sivan -> 40, Kai -> 31, David Guetta -> 27, Matoma -> 50, Kygo -> 38, Cheat Codes -> 29, The Chainsmokers -> 2, Timeflies -> 22, Bebe Rexha -> 48, ROZES -> 47, Rihanna -> 1, Sia -> 4)
  //  Map(Coffee Talk -> (6,53), All The Same -> (10,33), Rock Bottom -> (12,54), She Is The West Coast -> (16,12), Your Graduation -> (3,59), Open Casket -> (7,36), History -> (2,49), New York City -> (9,8), Looking Back -> (19,44), Time Capsules -> (17,27), 67, Cherry Red -> (1,39), Ophelia -> (5,76), Sleep On The Floor -> (13,68), Fine, Great -> (4,56), Life In Costumation -> (8,5), Affirmative! -> (18,5), Go Get A Switch And Turn Yourself On -> (14,3), Cabin Fever -> (11,33), No EQ -> (15,38), Hopeless Case -> (20,47))

  @Test
  def popularityWeightedMeanTest() = {
    val artistRankPop = new scala.collection.mutable.HashMap[String, (Int, Int)]() {
      put("ROAM", (8, 41));
      put("The Wonder Years", (1, 56));
      put("The Starting Line", (18, 48));
      put("That Was Something", (10, 9));
      put("Aaron West & The Roaring Twenties", (15, 43));
      put("Passion Pit", (9, 67));
      put("Fireworks", (16, 37));
      put("State Champs", (11, 58));
      put("Neck Deep", (12, 62));
      put("Say Anything", (14, 56));
      put("The Lumineers", (3, 78));
      put("Forever Came Calling", (13, 34));
      put("Modern Baseball", (2, 63));
      put("Into It. Over It", (4, 44));
      put("Motion City Soundtrack", (7, 56));
      put("Sundressed", (20, 13));
      put("Knuckle Puck", (19, 52));
      put("Broadside", (17, 43));
      put("Real Friends", (6, 57));
      put("The Story So Far", (5, 61))
    }
    //      val artistRankPop = new scala.collection.mutable.HashMap[String,(Int,Int)](){
    //        put("one",(1,75));
    //        put("two",(2,50));
    //        put("three",(3,50));
    //      }

    println(stats.popularityWeightedMean(artistRankPop))
  }

  @Test
  def percentageOfHitsTest() = {
    val artistRankPop = new scala.collection.mutable.HashMap[String, (Int, Int)]() {
      put("ROAM", (8, 41)); put("The Wonder Years", (1, 56)); put("The Starting Line", (18, 48)); put("That Was Something", (10, 9)); put("Aaron West & The Roaring Twenties", (15, 43)); put("Passion Pit", (9, 67)); put("Fireworks", (16, 37)); put("State Champs", (11, 58)); put("Neck Deep", (12, 62)); put("Say Anything", (14, 56)); put("The Lumineers", (3, 78)); put("Forever Came Calling", (13, 34)); put("Modern Baseball", (2, 63)); put("Into It. Over It", (4, 44)); put("Motion City Soundtrack", (7, 56)); put("Sundressed", (20, 13)); put("Knuckle Puck", (19, 52)); put("Broadside", (17, 43)); put("Real Friends", (6, 57)); put("The Story So Far", (5, 61))
    }
    val hitsArtistRank = new scala.collection.mutable.HashMap[String, Int]() {
      put("ROAM", 8); put("The Wonder Years", 1); put("The Starting Line", 18); put("That Was Something", 1); put("Aaron West & The Roaring Twenties", 15); put("Passion Pit", 9); put("Fireworks", 16); put("State Champs", 11); put("Neck Deep", 12); put("Say Anything", 14); put("The Lumineers", 3);
    }
    //    val artistRankPop = new scala.collection.mutable.HashMap[String, (Int, Int)]() {
    //      put("one", (1, 75));
    //      put("two", (3, 50));
    //      put("three", (17, 50));
    //    }
    //    val hitsRank = new scala.collection.mutable.HashMap[String, Int]() {
    //      put("one", 10);
    //      put("two", 5);
    //      put("four", 20);
    //    }

    println(stats.percentageOfHits(artistRankPop, hitsArtistRank))
  }
}
