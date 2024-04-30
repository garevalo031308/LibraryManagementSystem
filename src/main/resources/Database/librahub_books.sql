CREATE DATABASE  IF NOT EXISTS `librahub` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `librahub`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: librahub
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL,
  `title` longtext,
  `author` longtext,
  `genre` longtext,
  `type` longtext,
  `date` longtext,
  `publisher` longtext,
  `description` longtext,
  `image` longtext,
  `borrowed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1087264,'1984','George Orwell','Science Fiction','Book','01/01/1961','Signet Classic','“The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.”\n\nWinston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching...\n\nA startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish. No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.\n\n','/MediaCovers/1984.jpg',_binary '\0'),(1271204,'Harry Potter and the Cursed Child','J.K. Rowling','Fantasy','Book','07/25/2017','Generic','The eighth story, nineteen years later...\n\nIt was always difficult being Harry Potter, and it isn\'t much easier now that he is an overworked employee of the Ministry of Magic, a husband, and a father of three school-age children.\n\nWhile Harry grapples with a past that refuses to stay where it belongs, his youngest son, Albus, must struggle with the weight of a family legacy he never wanted. As past and present fuse ominously, both father and son learn the uncomfortable truth: sometimes, darkness comes from unexpected places.\n\nBased on an original new story by J.K. Rowling, Jack Thorne, and John Tiffany, a new play by Jack Thorne, \"Harry Potter and the Cursed Child\" is the complete and official playscript of the original, award-winning West End production. This updated edition includes the final dialogue and stage directions, a conversation piece between director John Tiffany and playwright Jack Thorne, the Potter family tree, and a timeline of events in the wizarding world leading up to \"Harry Potter and the Cursed Child.\"\n','/MediaCovers/HarryPotterandtheCursedChild.jpg',_binary '\0'),(2130518,'A Brief History of Time','Stephen Hawking','Non-Fiction','Book','09/01/1998','Random House Publishing Group','A landmark volume in science writing by one of the great minds of our time, Stephen Hawking’s book explores such profound questions as: How did the universe begin—and what made its start possible? Does time always flow forward? Is the universe unending—or are there boundaries? Are there other dimensions in space? What will happen when it all ends?\n\nTold in language we all can understand, A Brief History of Time plunges into the exotic realms of black holes and quarks, of antimatter and “arrows of time,” of the big bang and a bigger God—where the possibilities are wondrous and unexpected. With exciting images and profound imagination, Stephen Hawking brings us closer to the ultimate secrets at the very heart of creation.\n','/MediaCovers/ABriefHistoryofTime.jpg',_binary '\0'),(5030003,'The Outsider: A Novel','Stephen King','Horror','Book','06/04/2019','Gallery Books','Evil has many faces…maybe even yours in this #1 New York Times bestseller from master storyteller Stephen King.\n\nAn eleven-year-old boy’s violated corpse is discovered in a town park. Eyewitnesses and fingerprints point unmistakably to one of Flint City’s most popular citizens—Terry Maitland, Little League coach, English teacher, husband, and father of two girls. Detective Ralph Anderson, whose son Maitland once coached, orders a quick and very public arrest. Maitland has an alibi, but Anderson and the district attorney soon have DNA evidence to go with the fingerprints and witnesses. Their case seems ironclad.\n\nAs the investigation expands and horrifying details begin to emerge, King’s story kicks into high gear, generating strong tension and almost unbearable suspense. Terry Maitland seems like a nice guy, but is he wearing another face? When the answer comes, it will shock you as only Stephen King can.\n','/MediaCovers/TheOutsider.jpg',_binary '\0'),(5225100,'Dune','Frank Herbert','Science Fiction','Book','08/02/2005','Frank Herbert','Frank Herbert’s classic masterpiece—a triumph of the imagination and one of the bestselling science fiction novels of all time.\n\nSet on the desert planet Arrakis, Dune is the story of Paul Atreides—who would become known as Muad\'Dib—and of a great family\'s ambition to bring to fruition humankind\'s most ancient and unattainable dream.\n\nA stunning blend of adventure and mysticism, environmentalism and politics, Dune won the first Nebula Award, shared the Hugo Award, and formed the basis of what is undoubtedly the grandest epic in science fiction.\n','/MediaCovers/Dune.jpg',_binary '\0'),(5453817,'Diary of a Wimpy Kid Book #1','Jeff Kinney','Drama','Book','04/01/2007','Amulet Books','Being a kid can really stink. And no one knows this better than Greg. He finds himself thrust into middle school, where undersized weaklings share the hallways with kids who are taller, meaner, and already shaving. Greg is happy to have Rowley Jefferson, his sidekick, along for the ride. But when Rowley\'s star starts to rise, Greg tries to use his best friend\'s newfound popularity to his own advantage, kicking off a chain of events that will test their friendship in hilarious fashion.\n\nThe hazards of growing up before you\'re ready are uniquely revealed through words and drawings as Greg records them in his diary. But as Greg says: “Just don’t expect me to be all “Dear Diary” this and “Dear Diary” that.”\n\nLuckily for us, what Greg Heffley says he won’t do and what he actually does are two very different things.\n','/MediaCovers/DiaryOfAWimplyKidBook1.jpg',_binary '\0'),(5587883,'Percy Jackson and the Olympians: The Chalice of the Gods','Rick Riordan','Mythology','Book','09/26/2023','Disney Hyperion','The original heroes from The Lightning Thief are reunited for their biggest challenge yet: getting Percy to college when the gods are standing in his way.\n\nAfter saving the world multiple times, Percy Jackson is hoping to have a normal senior year. Unfortunately, the gods aren’t quite done with him. Percy will have to fulfill three quests in order to get the necessary three letters of recommendation from Mount Olympus for college. \n\nThe first quest is to help Zeus’s cup-bearer retrieve his goblet before it falls into the wrong hands. Can Percy, Grover, and Annabeth find it in time? \n\nReaders new to Percy Jackson (this book can be enjoyed as a standalone) and fans who have been awaiting this reunion for more than a decade will delight equally in this latest hilarious take on Greek mythology.\n','/MediaCovers/PJChaliceoftheGods.jpg',_binary '\0'),(6458744,'Macbeth','William Shakespeare','Drama','E-Book','07/01/2003','Simon & Schuster','The authoritative edition of Macbeth from The Folger Shakespeare Library, the trusted and widely used Shakespeare series for students and general readers.\n\nIn 1603, James VI of Scotland ascended the English throne, becoming James I of England. London was alive with an interest in all things Scottish, and Shakespeare turned to Scottish history for material. He found a spectacle of violence and stories of traitors advised by witches and wizards, echoing James’s belief in a connection between treason and witchcraft.\n\nIn depicting a man who murders to become king, Macbeth teases us with huge questions. Is Macbeth tempted by fate, or by his or his wife’s ambition? Why does their success turn to ashes?\n\nLike other plays, Macbeth speaks to each generation. Its story was once seen as that of a hero who commits an evil act and pays an enormous price. Recently, it has been applied to nations that overreach themselves and to modern alienation. The line is blurred between Macbeth’s evil and his opponents’ good, and there are new attitudes toward both witchcraft and gender.\n\nThe edition includes:\n-Freshly edited text based on the best early printed version of the play\n-Newly revised explanatory notes conveniently placed on pages facing the text of the play\n-Scene-by-scene plot summaries\n-A key to the play’s famous lines and phrases\n-An introduction to reading Shakespeare’s language\n-An essay by a leading Shakespeare scholar providing a modern perspective on the play\n-Fresh images from the Folger Shakespeare Library’s vast holdings of rare books\n-An up-to-date annotated guide to further reading\n','/MediaCovers/Macbeth.jpg',_binary '\0');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-29 20:27:16
