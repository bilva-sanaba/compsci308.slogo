# Fluxx

## Evaluate



## Classes

* Card abstract class
* Abstract subclass for each type of card
* Non-abstract subclass for each instance of each type of card
* Table class-keeps track of collections of cards: rules, win conditions, played cards, hands, deck, and discard pile
* User class knows how to take a turn. It can see its hand, played cards, etc.
* Cardczar class that changes collections of cards based on the rules of the card
* Turnplayer runs the game loop, telling each user when its their turn
* Hand is a collection of cards and adds  
* Deck

## Behaviors

* The Turnplayer tells each user, when their turn starts, the conditions it must meet to end its turn

* Drawing a card changes the user state
* Drawing a card is done automatically at the start of a player's turn until the drawing condition is met
* No cards may be played when the drawing condition is unmet
* The Cardczar prompts the user for a card to be played until the playing condition is met
* Playing a card changes the table state
* The card is sent to the Cardczar, which "plays" the card
* Playing the card sends it to the table and runs a unique "execute" method on the card
* The turnplayer evaulates after every action whether a win condition has been met


public User{
	
}


public abstract Card{
Holds a public method which 
	public abstract void cardAction(Table table){
  
  }
}

public abstract RuleCard{
	Rule myRule;
	public void cardAction(Table table){
  	table.setRule(myRule);
  }
}

public Cardczar{
	public void respondToUserPlayCard(String username, Card card)
  
  public String promptInput()
}


##Use Cases




