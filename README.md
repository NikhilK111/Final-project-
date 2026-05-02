The program overview 
 
This program is supposed to be like an arcade machine that allows the ability to switch between card base game. The current games in the code are blackjack and war. The user is allowed to switch between games through the menu, and you can replay them as many times as you like 

Alogrithems 
Deck Creation and Shuffling Algorithm, which creates a full deck of 52 cards and randomizes their order. 

War Game Loop Algorithm, which simulates the War card game until one player wins all the cards.

Blackjack Hand Value Calculation Algorithm, which calculates the total value of a Blackjack 
hand

Data structures 
Array(String)
Found in Deck.java,
 which stores the card rank and suits 

ArrayList<Card>
Found in Deck.java, Hand.java, and War.java, 
which store the full deck of cards, store player hands in Blackjack, and store player piles and war “pot” in War

Scanner
Found in Blackjack.java and Menu.java 
This allows me to combine classes 

String 
Found in Card.java and menu.java
This is  necessary for representing readable game information


Development Reflection
One major issue I came across during the development of the code was the attempt in trying to add the snake game code, which was written in JavaScript, which I had to try to rewrite in Java, which took a lot of time and caused a lot more issues, like compatibility. During this process, I had to edit the blackjack code into a graphical version, but later, we scrapped the snake game code, which forced me to try to revert the blackjack code to the original state. Since I forgot to save the most recent code, I had to go to an older, more unfinished code, in which I had to recode, which led to missing features and code that I found myself to be not up to standards in my opinion 


Another challenge was my limited knowledge of Java beyond what I’ve learned in class. This made implementing features much more difficult, and it took the most time; additionally, working on a slower computer increased frustration and development time. 

What I want to add to a future version of this project I would want to add is a more arcade-style game. A graphical user interface instead of a console-based output. AI-controlled opponents to be able to add more games, such as Uno or Goldfish. Finally, improve the menu system to make it more interactive and visually appealing 


Flowchart on the card 

[Start]
   ↓
[Set ranks + suits arrays]
   ↓
[Loop ranks]
   ↓
[Loop suits]
   ↓
[Create Card]
   ↓
[Add to ArrayList]
   ↓
[Shuffle deck]
   ↓
[End]

Flowchart Blackjack 

[Start Round]
   ↓
[Create deck]
   ↓
[Deal cards]
   ↓
[Player turn]
   ↓
{Hit / Stand / Double?}
   ↓
[Update hand]
   ↓
{Player > 21?}
   ↓Yes        ↓No
[Lose]     [Dealer turn]
              ↓
        [Dealer draws until 17+]
              ↓
        [Compare results]
              ↓
             [End]

War flowchart 
         [Start]
               ↓
        [Shuffle deck]
               ↓
        [Split cards 26/26]
               ↓
       {Both players have cards?}
               ↓Yes
        [Draw 1 card each]
               ↓
        {Compare values}
               ↓
 
 ↓              ↓               ↓
P1 wins      P2 wins        Tie (WAR)
 ↓              ↓               ↓
Add cards    Add cards   [War sequence]
                               ↓
                      [3 face down each]
                               ↓
                      [Flip card + compare]
                               ↓
                      [Winner takes pot]
                               ↓
                      (repeat if tie)
   ↓
[Check game end]
   ↓
[End] 