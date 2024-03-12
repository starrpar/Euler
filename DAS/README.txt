Instructions for running this App / Java project:

NOTE: haven't created *.jar files in a long time - created one using "jar cf DonationDistribution.jar classes\*.class" command - 
hopefully that works; adding it to GitHub, in case that will make it work for everyone else...

I built a DB in MySQL - very trivial, 2 tables - not sure how to best "convey" that to you - if user/evaluator was to install MySQL 
themselves and then modify my code to create their connection and security (user/password), then it should be runnable... 
(would have to create the following tables like I did)...

If I need to package it fully, please advise me and I will pursue doing so - wasn't sure where the line was on that.

...one for Donations data:
Columns:
ID (int, auto_incremented, not nullable)
DonorName (string/varchar(45), not nullable, no default (will fail if not provided))
DonationType (enum['MONEY', 'FOOD', 'CLOTHING', 'MISC'], not nullable) (would have types added if needed; expected not to continue 
to grow after initial changes) - default 'MISC'
DollarValue (decimal(10,2), not nullable) - default 0.00
Date (datetime, nullable)
Remaining (decimal(10,2), nullable)

...and one for Distributions data:
Columns:
ID (int, auto_incremented, not nullable)
DonationType (enum['MONEY', 'FOOD', 'CLOTHING', 'MISC'], not nullable) (would have types added if needed; expected not to continue 
to grow after initial changes) - default 'MISC'
DollarValue (decimal(10,2), not nullable) - default 0.00
Date (datetime, nullable)

Other use instructions would simply be:
- compiling the java source code and executing it, either in an IDE, or via running javac.exe 
and java.exe against the executable.

I would be happy to understand further what "automatic execution" level you would want me to reach before "handing it over", 
but if this is mostly for seeing my code in GitHub, then I was thinking stopping at this point was appropriate 
(but don't want to be too presumptuous).  I certainly understand making a "finished product" (packaged for execution) is
what the standard would be for presenting to the actual customer.

I will pursue creating this as a packaged JAR file with MySQL contained, but need to review some of that to do it easily/quickly.