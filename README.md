# Insurance Application, Java4Web
A java application which will cover the function specs below, regarding a real life scenarion of vehicle's
insurance and ownership information.

F1: Vehicle Insurance Status
Given a vehicle's plate, the application should respond with the vehicle's insurance status.
-The user should provide the plate as an argument in the command line.
-The plate's pattern should be provided in "ABC-1234" format. In case different pattern is provided, the application should
respond with an error message.

F2: Vehicles' Insurance that are about to expire
Given a time frame in days, the application should provide a list of the vehicles of whichtheir license is going to expire
within this timeframe (what is to expire in the next X days).
-User should provide the number of days
-User should provide the export format (console, or exported file)

F3: Sorting of the plates-number
The application, if requested, can provide the plate numbers order in alphanumerical order. The participants should design an algorithm to sort plates-number in ascending manner.

F4: Fine calculation per owner
In case that an owner may hava one or more uninsured vehicles, the application should be able to calculate the total fine cost, according to a fine which will be provided as an argument in the prompt.

Note: A vehicle cannot be physically owned by more than one person.
