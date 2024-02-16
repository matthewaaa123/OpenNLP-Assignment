package code;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import java.io.FileInputStream;
import java.io.IOException;

public class hello {

    public static void main(String[] args) {

        String News = "Anglo-French Channel Tunnel operator Eurotunnel Monday announced a deal giving its creditor banks 45.5 percent of the company in return for wiping out one billion pounds ($1.56 billion) of its debt. The long-awaited restructuring brings to an end months of wrangling between Eurotunnel and the 225 banks to which it owes nearly nine billion pounds ($14.1 billion). The deal, announced simultaneously in Paris and London, brings the company back from the brink of insolvency but leaves shareholders owning only 54.5 percent of the company. \"The restructuring plan provides Eurotunnel with the medium-term financial stability to allow it to consolidate its substantial commercial achievements to date and to develop its operations,\" Eurotunnel co- chairman Alastair Morton said. The firm was now making a profit before interest, he added. Although shareholders will see their interests diluted, they were offered the prospect of a brighter future after months of uncertainty while Eurotunnel wrestled to reduce crippling interest payments negotiated during the tunnel's construction. Eurotunnel, which has taken around half the cross-Channel market from the European ferry companies, said a strong operating performance could allow it to pay its first dividend within the next 10 years. French co-chairman Patrick Ponsolle said shareholders would have to be patient before they could reap the benefits of the company's success. He called the debt restructuring plan \"an acceptable compromise\" for holders of Eurotunnel shares. The company said there was still considerable work to be done to finalise and agree on the details of the plan before it can be submitted to shareholders and the full 225 bank syndicate for approval, probably early in 1997. Monday\'s announcement followed two weeks of highly secretive negotiations between Eurotunnel and its six leading banks. This was extended to the 24 \"instructing banks\" at a meeting late last week in London. Eurotunnel said the debt-for-equity swap would be at 130 pence, or 10.40 francs, per share. That is considerably below the level of around 160 pence widely reported before announcement of the deal, and will reduce outstanding debt of 8.7 billion pounds ($13.6 billion) by 1.0 billion ($1.56 billion). The company said a further 3.7 billion pounds ($5.8 billion) of debt would be converted into new financial instruments and existing shareholders would be able to participate in this issue. If they choose not to take up free warrants entitling them to subscribe to this, Eurotunnel said shareholders\' interests may be reduced further to just over 39 percent of the company by the end of December 2003. Eurotunnel's shares, which were suspended last week at 113.5 pence ahead of Monday\'s announcement, should resume trading on Tuesday, the company said.";

        try {
        	
            // Load the sentence detection model
            SentenceModel sentenceModel = new SentenceModel(new FileInputStream("en-sent.bin"));
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);

            // Load the tokenizer model
            TokenizerModel tokenizerModel = new TokenizerModel(new FileInputStream("en-token.bin"));
            TokenizerME tokenizer = new TokenizerME(tokenizerModel);
            
            // Load the POS tagging model
            POSModel posModel = new POSModel(new FileInputStream("en-pos-maxent.bin"));
            POSTaggerME posTagger = new POSTaggerME(posModel);
            
            // Load the name finder model for person names
            TokenNameFinderModel personModel = new TokenNameFinderModel(new FileInputStream("en-ner-person.bin"));
            NameFinderME personFinder = new NameFinderME(personModel);

            // Load the name finder model for locations
            TokenNameFinderModel locationModel = new TokenNameFinderModel(new FileInputStream("en-ner-location.bin"));
            NameFinderME locationFinder = new NameFinderME(locationModel);
            
            // Load the name finder model for organizations
            TokenNameFinderModel organizationModel = new TokenNameFinderModel(new FileInputStream("en-ner-organization.bin"));
            NameFinderME organizationFinder = new NameFinderME(organizationModel);
            
            // Load the name finder model for dates
            TokenNameFinderModel dateModel = new TokenNameFinderModel(new FileInputStream("en-ner-date.bin"));
            NameFinderME dateFinder = new NameFinderME(dateModel);

            // Load the name finder model for money
            TokenNameFinderModel moneyModel = new TokenNameFinderModel(new FileInputStream("en-ner-money.bin"));
            NameFinderME moneyFinder = new NameFinderME(moneyModel);

            // Load the name finder model for percentages
            TokenNameFinderModel percentageModel = new TokenNameFinderModel(new FileInputStream("en-ner-percentage.bin"));
            NameFinderME percentageFinder = new NameFinderME(percentageModel);

            // Load the name finder model for time
            TokenNameFinderModel timeModel = new TokenNameFinderModel(new FileInputStream("en-ner-time.bin"));
            NameFinderME timeFinder = new NameFinderME(timeModel);


            // Detect sentences
            String[] sentences = sentenceDetector.sentDetect(News);

            int i = 1;
            
            // Output detected sentences
            for (String sentence : sentences) {
            	
            	System.out.println("\u001B[31m------------\u001B[0m");
            	System.out.println("\u001B[31mSentence " + i + "\u001B[0m");
            	System.out.println("\u001B[31m------------\u001B[0m");
            	System.out.println("");
                System.out.println("\u001B[31mSentence:\u001B[0m");
                System.out.println(sentence);
                System.out.println("");
                
                // Tokenize each sentence
                String[] tokens = tokenizer.tokenize(sentence);
                
                // Output tokens
                System.out.println("\u001B[31mTokens:\u001B[0m");
                for (String token : tokens) {
                    System.out.println("'" + token + "'");
                }
                
                // Perform POS tagging
                String[] tags = posTagger.tag(tokens);

                // Output POS tags
                System.out.println("\n\u001B[31mPOS Tags:\u001B[0m");
                for (int j = 0; j < tokens.length; j++) {
                    System.out.println(tokens[j] + "/" + tags[j]);
                }
                
                // Perform named entity recognition for persons
                Span[] personNames = personFinder.find(tokens);

                // Output person names
                System.out.println("\n\u001B[31mPerson Names:\u001B[0m");
                for (Span name : personNames) {
                    System.out.println(name);
                }

                // Perform named entity recognition for locations
                Span[] locations = locationFinder.find(tokens);

                // Output locations
                System.out.println("\n\u001B[31mLocations:\u001B[0m");
                for (Span location : locations) {
                    System.out.println(location);
                }
                
                // Perform named entity recognition for organizations
                Span[] organizations = organizationFinder.find(tokens);

                // Output organizations
                System.out.println("\n\u001B[31mOrganizations:\u001B[0m");
                for (Span organization : organizations) {
                    System.out.println(organization);
                }
                
                // Perform named entity recognition for dates
                Span[] dates = dateFinder.find(tokens);

                // Output dates
                System.out.println("\n\u001B[31mDate:\u001B[0m");
                for (Span date : dates) {
                    System.out.println(date);
                }
                
                // Perform named entity recognition for money
                Span[] money = moneyFinder.find(tokens);

                // Output money
                System.out.println("\n\u001B[31mMoney:\u001B[0m");
                for (Span dollar : money) {
                    System.out.println(dollar);
                }
                
                // Perform named entity recognition for percentages
                Span[] percentages = percentageFinder.find(tokens);

                // Output percentages
                System.out.println("\n\u001B[31mPercentages:\u001B[0m");
                for (Span percent : percentages) {
                    System.out.println(percent);
                }
                
                // Perform named entity recognition for time
                Span[] times = timeFinder.find(tokens);

                // Output time
                System.out.println("\n\u001B[31mTimes:\u001B[0m");
                for (Span time : times) {
                    System.out.println(time);
                }
                
                System.out.println();
                
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
