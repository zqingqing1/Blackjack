import java.util.Scanner;

public class Blackjack{
	public static void main(String[] args){
		int money;
		int bet;
		boolean userWins;

		System.out.println("Welcome to the BlackJack\n");

		money=100;
		while(true){
			System.out.println("You have "+money+"$");
			do{
				System.out.println("How many dollars do you want to bet? <Enter 0 to "+money+">");
				Scanner scan=new Scanner(System.in);
				bet=scan.nextInt();
				if(bet<0||bet>money)
					System.out.println("Please Input valid bet number");
			}while(bet<0||bet>money);

			if(bet==0)
				break;
			userWins=playBlackjack();

			if(userWins)
				money=money+bet;
			else
				money=money-bet;

			if(money==0){
				System.out.println("Looks like you've out of money!");
				break;
			}
		}

		System.out.println("You leave with $"+money);

	}

	static boolean playBlackjack(){
		Deck deck;
		BlackjackHand dealerHand;
		BlackjackHand userHand;

		deck=new Deck();
		dealerHand = new BlackjackHand();
		userHand=new BlackjackHand();

		deck.shuffle();
		dealerHand.addCard(deck.dealCard());
		dealerHand.addCard(deck.dealCard());
		userHand.addCard(deck.dealCard());
		userHand.addCard(deck.dealCard());

		if(dealerHand.getBlackjackValue()==21){
			System.out.println("Dealer has the "+ dealerHand.getCard(0)+" and the "+dealerHand.getCard(1));
			System.out.println("User has the "+ userHand.getCard(0)+" and the "+userHand.getCard(1));

			System.out.println("Dealer has Blackjack. Dealer wins!");
			return false;

		}

		if(userHand.getBlackjackValue()==21){
			System.out.println("Dealer has the "+ dealerHand.getCard(0)+" and the "+dealerHand.getCard(1));
			System.out.println("User has the "+ userHand.getCard(0)+" and the "+userHand.getCard(1));

			System.out.println("You have Blackjack. You win!");
			return true;

		}

		while(true){
			System.out.println("Your cards are:");
			for(int i=0;i<userHand.getCardCount();i++){
				System.out.println("	"+userHand.getCard(i));
			}

			System.out.println("Your total value is "+userHand.getBlackjackValue());
			System.out.println("Dealer is showing the "+dealerHand.getCard(0));
			System.out.println("Hit(H) or Stand(S)?");
			String userAction;
			do{	
				Scanner scan = new Scanner(System.in);
				userAction=scan.next().toUpperCase();
				if(!userAction.equals("H")&&!userAction.equals("S"))
					System.out.println("Hit(H) or Stand(S)?");
			}while(!userAction.equals("H")&&!userAction.equals("S"));


			if(userAction.equals("S"))
				break;
			else{
				Card newcard=deck.dealCard();
				userHand.addCard(newcard);
				System.out.println("User hits");
				System.out.println("Your card is "+newcard);
				System.out.println("Your total is now "+userHand.getBlackjackValue());
				if(userHand.getBlackjackValue()>21){
					System.out.println("You busted by going over 21. You lose.");
					System.out.println("Dealer's otjer card was the "+dealerHand.getCard(1));
					return false;
				}
			}
		}

		System.out.println("User stands.");
		System.out.println("Dealer's cards are:");
		System.out.println("	"+dealerHand.getCard(0));
		System.out.println("	"+dealerHand.getCard(1));

		while(dealerHand.getBlackjackValue()<=16){
			Card newcard=deck.dealCard();
			dealerHand.addCard(newcard);
			System.out.println("Dealer hits and get "+newcard);
			if(dealerHand.getBlackjackValue()>21){
				System.out.println("Dealer busted by going over 21. You win.");
				return true;
			}

		}
		System.out.println("Dealer's totla is "+dealerHand.getBlackjackValue());
		if(dealerHand.getBlackjackValue()==userHand.getBlackjackValue()){
			System.out.println("Dealer wins, you both have the same value");
			return false;
		}else if(dealerHand.getBlackjackValue()>userHand.getBlackjackValue()){
			System.out.println("Dealer wins, larger than yours" );
			return false;
		}else{
			System.out.println("You win");
			return true;
		}
	}
}
