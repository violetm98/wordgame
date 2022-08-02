### Design Information
***
1. **When the application is started, the player may choose to (1) Play a word game, (2) View statistics, or (3) Adjust the game settings.**

    When user opens the app, Main activity will instantiate a new app object with the default setting.
Three buttons are displayed on the home page: "play game", "view statistics", and "adjust settings".
When user presses any of the buttons, an event with the corresponding event type will be captured by the listener. App then will call the specific method
based on the event type. Types of event will be stored in enum class.

    Following methods in the app class is to handle "play game", "view statistics" and "adjust setting" button click
      - playWordGame method to handle "PLAY_GAME" event
      - adjustSetting method to handle "ADJUST_SETTING" event
      - viewGameStats and viewWordStats method to handle "VIEW GAME_STATISTICS" and "VIEW_WORD_STATISTICS
      " events

    App class has a setting instance variable reflecting the current state of app settings

    Example:
  User presses "play game" button --> event "PLAY GAME" --> listener --> app.playGame()

2.  **When choosing to adjust the game settings, the player (1) may choose for the game to end after a certain number of minutes, from 1 to 5, defaulting to 3, (2) may adjust the size of the square board, between 4(x4) and 8(x8), defaulting to 4, and (3) may adjust the weights of the letters of the alphabet between 1 and 5, defaulting to 1.**

    App class will have an adjustSetting method to handle "ADJUST_SETTING" event. The method will take three user input as arguments: size (int) for the size of the board,
 weight<character, int> that stores character as key and character weight as value, and numMinutes (int) for the length of the game. The method will use the setter method
 in the Setting class to update the value of numMinutes, size and weight of the letters in the setting object according to user input.

3. **When choosing to play a word game, a player will:**

        a. Be shown a ‘board’ of randomly generated letters.
        ====================================================
        The playGame method will instantiate a new game object with the app setting.
        The game object will be saved in a variable in the Main activity. The game object will call
        the generateBoard method to create a new board. generateBoard method will instantiate a new board
        object with the following instance variables:
          - cells of type Map<Tuple, Cell> is instantiated. The Tuple indicates
            the x, y coordinate of each cell of the board, and the Cell points to a Cell object. 
            the Cell object has two instance variable: used of Boolean type to indicate whether the cell
            has been used and letter of String type to map the cell to the corresponding assigned letter.
          - vowels and consonants of String type which contains the vowels and consonants letters respectively
          - size of Integer type
          - weight of Map<character, int> type which maps each character to its weight
        The board object will then call the createCells method to create an empty board.The method will
        generate a pair of x, y coordinate stored in a Tuple and a cell object with used flag set to false
        and letter set to null and update cells Map with the key value pair. 
        In total, (size^2) number of key value pairs will be added

        board object will then call the adjustWeight method to adjust the vowels and consonants string based on
        the weight map. For example if a is mapped to weight of 4, then a is appended to the
        vowels string for n-1 times. The method will return weighted vowels and consonants string

        board object will then call generateLetters method to generate the final weighted sample string.
        The method will use a random generator to generate 1/5*(size^2) number of vowels from the weighted vowels string.
        Similarly the method will take the weighted consonants and randomly generates 4/5*(size^2) consonants.
        Two randomly generated strings will be concatenated and letters are reshuffled

        Finally board will call the fillBoard method to fill all the cells. The fillBoard method 
        will iterate through the reshuffled string generated from the generateLetters method and update 
        each cell object in the cells map with the corresponding letter. Once the board is filled it will 
        be displayed in the UI. Note that for letter Q, 'Qu' will be assigned as the letter instead of Q 
        as indicated by the game rule

        b. Be shown a timer counting down the number of minutes available for the game, as set in the settings.
        =======================================================================================================
        game object will call the setTimer method to create a new Timer object based on the app setting. 
        Timer object has instance variable timeLeft of double type which reflects the time remaining. 
        timeLeft is initially set based on numMinutes variable in the setting object.

        c. Start with 0 points, which is not required to be displayed during the game.
        ==============================================================================
        points variable is initially set to zero when game object is instantiated

        d. Until the game timer counts to zero and the game ends:
          i. Enter a unique word made up of two or more letters on the board.
             The word must contain only letters from the board that are each adjacent to the
             next (horizontally, vertically, or diagonally) and a single letter on the board may not
             be used twice. The word does not need to be checked against a dictionary (for simplicity, we
             will assume the player enters only real words that are spelled correctly).
            ==============================================================================================
             Once user enters a new word, a "HANDLE_INPUT" event with an ArrayList of Tuple included in the
             event will be captured by the listener. The event will trigger game object to call 
             handleInput methods with ArrayList of Tuple as input parameter. ArrayList contains 
             the x, y coordinate of each entered letter. The handleInput method will call the checkInput
             method with the board instance variable. The checkInput method iterates the ArrayList of Tuples 
             and check 1. whether all letters are adjacent based on the x, y coordinate and 2. whether a letter
             has already been used by checking the used flag of each corresponding cell. If validation passed,
             the method will call the updateBoard method to update the used flag of corresponding cells to true
             and return entered word. If validation fails, UI will display an error message and an empty string
             will be returned. The returned word(if non empty)  will then be added to the listWords
             variable in the Game class.

          ii or Choose to re-roll the board at a cost of 5 points. The board will be
             re-created in the same way it is generated at the start of each game, replacing
             each letter. The timer will not be reset or paused. The player’s score may go into
             negative values.
             ===================================================================================
             when user press the "reroll" button, a "REROLL" event will be captured by the listener.
             The event will trigger the game object to call reRoll method, which will call the 
             generateBoard method to create a new board and add 1 to the numReRolls variable

          iii or Choose to exit the game early.
             ===================================
             When user pressed the exit button, an "END_GAME" event will be generated similarly to
             when the timer is up. The "END GAME" event will trigger game object to call exit method 
             which will then call displayScore method to display the final score.

        e. At the end of the game, when the timer has run out or the player chooses to exit,
           the final score for the game will be displayed.
           =================================================================================
           Timer object calls the updateRemainingTime function to update the timeLeft variable
           every 10 seconds. once timeLeft reaches zero, Timer object will call pushToListener with 
           event type "END GAME". The "END GAME" event will trigger game object to call exit method. 
           Exit method will call displayScore method to displayed the final score. displayScore method will 
           iterate the listWords and add points to the point variable based on word length
           Then it will deduct 5 * numReRolls points from the point variable and finally 
           display the score to UI

        f.Each word will score 1 point per letter (‘Qu’ will count as 2 letters), and
          the cost for each board reset will be subtracted.
          ============================================================================
          This is explained in e above

        g. After the player views the score, they will continue back to the main menu
          ============================================================================
          User can press the main button to go back to main menu. a "DISPLAY_HOME" event will be generated
          when user press the main button. Event is captured by the listener and home screen will be displayed

 4. **Whenever the board is generated, or re-generated it will meet the following criteria:**

         a. The board will consist of a square full of letters.  The square should have the number of 
            letters, both horizontally and vertically, indicated by the size of the square board from 
            the game settings (4x4, 5x5, 6x6, 7x7, or 8x8).
            =======================================================================================
             This is explained in 3a

         b. ⅕ (rounded up) of the letters will be vowels (a,e,i,o,u). ⅘ will be consonants.
            ===============================================================================
             This is explained in 3a

         c. The letter Q will be displayed as ‘Qu’ (so that Q never appears alone).
            =======================================================================
             This is explained in 3a

         d. The location and particular letters should be randomly selected from a distribution
             of letters reflecting the weights of letters from the settings. A letter with a weight
             of 5 should be 5 times as likely to be chosen as a letter with a weight of 1 (assuming
             both are consonants or both are vowels).  In this way, more common letters can be set 
             to appear more often.
             =======================================================================================            
              This is explained in 3a

          e. A letter may appear on the board more than once.
             ====================================================
             This is explained in 3a
5. **When choosing to view statistics, the player may view (1) game score statistics, or (2) word statistics.**
    
    view statistics will be displayed as a drop down with two options: game score statistics and word statistics

6. **For game score statistics, the player will view the list of scores, in descending order by final game score, displaying:**

          a. The final game score
          b. The number of times the board was reset
          c. The number of words entered in the game
          The player may select any of the game scores from this list to view the settings for that 
          game’s board size, number of minutes,
          and the highest scoring word played in the game (if multiple words score an equal number 
          of points, the first played will be displayed).
    
    A new appManger singleton object will be created in the Main activity when launching the application. 
    appManger has an instance variable of type list<Game> which stores played games. whenever there 
    is a "PLAY_GAME" event, appManger singleton object will call register method to register game by 
    adding the game object to the games list. All played games are stored in a singleton class so that
    game statistics can be easily accessed from multiple classes

    if user selects game score statistics in the view Statistics drop down, a "VIEW_GAME_STATISTICS" event
    will be generated and captured by the event listener. The event will trigger app to call viewGameStats 
    method. The method will instantiate a new GameStatistics object. The object will then call generateStats
    method. The method will initiate the appManager singleton object and get the games list from the appManger by
    using the getter method in the appManager class. Then the method will sort the list of game objects 
    using point field in Game class in descending order. When point equals, sort based on numReRolls in 
    ascending order. When numReRolls equal, sort based on words entered (length of listWords ArrayList) in
    descending order. Game class can implement Comparable interface to implement the sorting logic. 
    The sorted game list is added to the appManager.Finally display all the required stats to the UI.

    When user selects a game score to view settings of that game, a "SELECT_GAME" event will be generated 
    along with the index of the selected item. The event will trigger app object to call selectSetting method. 
    This method will instantiate a new GameStatistics object. The object will call selectScore method. 
    selectScore method will initialize the appManger, retrieve the sorted game list and get the game object by
    indexing the list using the index of the selected game. Finally it will display the required fields 
    (size, numMinutes, highest scoring word) to the UI. Highest scoring word can be calculated by sorting 
    the listWords by word length in descending order and pick the first element in the sorted list. Note
    if word length equals the the first played will be displayed

7. **For the word statistics, the player will view the list of words used, starting from the most frequently played, displaying:**

          a. The word
          b. The number of times the word has been played, across all games

   When users selects word statistics in the drop down, a new "VIEW_WORD_STATS" will be generated. The event
   trigger app object to call viewWordStats method. This method will instantiate a new WordStatistics object. 
   The object will call generateStats method. The method will initiate the appManger object and
   retrieve the game list and reverse the game list so that the most recently played game appears first in the list.
   The method will create a map that stores the word as key and number of times played as value. 
   The method will then iterate the reversed game list, retrieve the listWords from each game object and
   iterate the listWords. Each word will be added to a final wordlist to be displayed and number of times 
   played of the word will be updated in the map as well.
   Finally all words and number of times the words have been played are displayed to the UI

8. **The user interface must be intuitive and responsive.**

    This is not represented in my design, as it will be handled entirely within the GUI implementation
9. **The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.**

    We assume that The device where this app is running will have enough memory and CPU resources. All persistent object will be backed
    by in memory database with a configurable cache limit. time consuming process will be handled in a separate thread
10. **For simplicity, you may assume there is a single system running the application.**

    
