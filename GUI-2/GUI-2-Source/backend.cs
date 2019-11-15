using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GUI2
{
    class backend
    {
        // game board array (0-63), 1 = player, -1 = AI, 0 = empty
        public int[] gameBoard;

        // scores array
        public int[] scores = null;

        // current player
        //String currentPlayer;


        // 76 total rows/cols/diags
        public static int[][] wins = new int[][]
        {
            new int[] {0, 1, 2, 3},       new int[] {0, 4, 8, 12},      new int[] {0, 16, 32, 48},    new int[] {0, 5, 10, 15},     new int[] {0, 20, 40, 60},
            new int[] {4, 5, 6, 7},       new int[] {1, 5, 9, 13},      new int[] {1, 17, 33, 49},    new int[] {16, 21, 26, 31},   new int[] {1, 21, 41, 61},
            new int[] {8, 9, 10, 11},     new int[] {2, 6, 10, 14},     new int[] {2, 18, 34, 50},    new int[] {32, 37, 42, 47},   new int[] {2, 22, 42, 62},
            new int[] {12, 13, 14, 15},   new int[] {3, 7, 11, 15},     new int[] {3, 19, 35, 51},    new int[] {48, 53, 58, 63},   new int[] {3, 23, 43, 63},
            new int[] {16, 17, 18, 19},   new int[] {16, 20, 24, 28},   new int[] {4, 20, 36, 52},    new int[] {3, 6, 9, 12},      new int[] {12, 24, 36, 48},
            new int[] {20, 21, 22, 23},   new int[] {17, 21, 25, 29},   new int[] {5, 21, 37, 53},    new int[] {19, 22, 25, 28},   new int[] {13, 25, 37, 49},
            new int[] {24, 25, 26, 27},   new int[] {18, 22, 26, 30},   new int[] {6, 22, 38, 54},    new int[] {35, 38, 41, 44},   new int[] {14, 26, 38, 50},
            new int[] {28, 29, 30, 31},   new int[] {19, 23, 27, 31},   new int[] {7, 23, 39, 55},    new int[] {51, 54, 57, 60},   new int[] {15, 27, 39, 51},
            new int[] {32, 33, 34, 35},   new int[] {32, 36, 40, 44},   new int[] {8, 24, 40, 56},    new int[] {0, 17, 34, 51},    new int[] {0, 21, 42, 63},
            new int[] {36, 37, 38, 39},   new int[] {33, 37, 41, 45},   new int[] {9, 25, 41, 57},    new int[] {4, 21, 38, 55},    new int[] {3, 22, 41, 60},
            new int[] {40, 41, 42, 43},   new int[] {34, 38, 42, 46},   new int[] {10, 26, 42, 58},   new int[] {8, 25, 42, 59},    new int[] {12, 25, 38, 51},
            new int[] {44, 45, 46, 47},   new int[] {35, 39, 43, 47},   new int[] {11, 27, 43, 59},   new int[] {12, 29, 46, 63},   new int[] {15, 26, 37, 48},
            new int[] {48, 49, 50, 51},   new int[] {48, 52, 56, 60},   new int[] {12, 28, 44, 60},   new int[] {3, 18, 33, 48},
            new int[] {52, 53, 54, 55},   new int[] {49, 53, 57, 61},   new int[] {13, 29, 45, 61},   new int[] {7, 22, 37, 52},
            new int[] {56, 57, 58, 59},   new int[] {50, 54, 58, 62},   new int[] {14, 30, 46, 62},   new int[] {11, 26, 41, 56},
            new int[] {60, 61, 62, 63},   new int[] {51, 55, 59, 63},   new int[] {15, 31, 47, 63},   new int[] {15, 30, 45, 60}
        };

        // constructor (<24 lines)
        public backend()
        {
            gameBoard = new int[64];
            clearGameBoard();
        }

        // clears game board by setting every spot to empty (<24 lines)
        public void clearGameBoard()
        {
            for (int boardIndex = 0; boardIndex < gameBoard.Length; boardIndex++)
            {
                gameBoard[boardIndex] = 0;
            }
        }

        // makes a valid move (<24 lines)
        public bool makeMove(int moveIndex, int player)
        {
            if (gameBoard[moveIndex] == 0)
            {
                gameBoard[moveIndex] = player;
                return true;
            }
            else
            {
                System.Console.WriteLine("Not a valid move!");
                return false;
            }
        }

        // checks for a win, returns 1 if player win, -1 if AI win, 0 if no win yet (<24 lines)
        public int victoryCheck()
        {
            for (int boardIndex = 0; boardIndex < 76; ++boardIndex)
            {
                if (gameBoard[wins[boardIndex][0]] != 0 &&
                gameBoard[wins[boardIndex][0]] == gameBoard[wins[boardIndex][1]] &&
                gameBoard[wins[boardIndex][0]] == gameBoard[wins[boardIndex][2]] &&
                gameBoard[wins[boardIndex][0]] == gameBoard[wins[boardIndex][3]])
                {
                    return gameBoard[wins[boardIndex][3]];
                }
            }
            return 0;
        }

        // returns array of victory moves (<24 lines)
        public int[] getVictoryMoves()
        {
            for (int boardIndex = 0; boardIndex < 76; ++boardIndex)
            {
                if (gameBoard[wins[boardIndex][0]] != 0 &&
                    gameBoard[wins[boardIndex][0]] == gameBoard[wins[boardIndex][1]] &&
                    gameBoard[wins[boardIndex][0]] == gameBoard[wins[boardIndex][2]] &&
                    gameBoard[wins[boardIndex][0]] == gameBoard[wins[boardIndex][3]])
                {

                    return wins[boardIndex];
                }
            }
            return null;
        }

        // calculates utility score for a board-state (<24 lines w/o comments)
        public int utility()
        {
            int total4X = 0; int total4O = 0;
            int total3X = 0; int total3O = 0; int total3X1O = 0;
            int total2X = 0; int total2O = 0;
            int total1X = 0; int total1O = 0;
            for (int numGroup = 0; numGroup < 70; numGroup++)
            {
                int numX = 0; int numO = 0; int numBlanks = 0;
                for (int numIndex = 0; numIndex < 4; numIndex++)
                {
                    // count all X/O/blanks in all r/c/d (X=1, O=-1, black=0)
                    if (gameBoard[wins[numGroup][numIndex]] == 1) { numX++; }
                    else if (gameBoard[wins[numGroup][numIndex]] == -1) { numO++; }
                    else if (gameBoard[wins[numGroup][numIndex]] == 0) { numBlanks++; }
                }
                // determine # of X's/blanks in each r/c/d
                if (numX == 4) { total4X++; }
                else if (numX == 3 && numBlanks == 1) { total3X++; }
                else if (numX == 2 && numBlanks == 2) { total2X++; }
                else if (numX == 1 && numBlanks == 3) { total1X++; }
                else if (numX == 3 && numO == 1) { total3X1O++; }
                // determine # of O's/blanks in each r/c/d
                if (numO == 4) { total4O++; }
                else if (numO == 3 && numBlanks == 1) { total3O++; }
                else if (numO == 2 && numBlanks == 2) { total2O++; }
                else if (numO == 1 && numBlanks == 3) { total1O++; }
            }
            return (utility_calc(total4X, total4O, total3X1O, total3X, total3O, total2X, total2O, total1X, total1O));
        }

        // helper function for calculating utility (<24 lines)
        public int utility_calc(int t4X, int t4O, int t3X1O, int t3X, int t3O, int t2X, int t2O, int t1X, int t1O)
        {
            int utilityScore = 10000 * (t4X - t4O) +
                        -1000 * (t3X1O) +
                           100 * (t3X - t3O) +
                            10 * (t2X - t2O) +
                                 (t1X - t1O);
            return utilityScore;
        }

        // This function is implmented with more than 24 lines as a conscious design choice
        // so it could all be in one function instead of 2 separate min & max functions
        // minimax algorithm with depth-limited search  
        public int minimax(int depth, bool isMax)
        {

            // terminate at this depth (affects performance)
            if (depth == 15)
            {
                return 0;
            }

            // maximizer's move (AI = -1)
            if (isMax)
            {
                // get utility score for current board-state
                int score = utility();
                if (depth == 0)
                {
                    return score;
                }
                int best = -1000;

                // iterate through all moves, recursive call to minimax, then undo all moves
                for (int boardIndex = 0; boardIndex < gameBoard.Length; boardIndex++)
                {
                    if (depth != 15 || victoryCheck() != -1)
                    {
                        if (gameBoard[boardIndex] == 0)
                        {
                            gameBoard[boardIndex] = -1;
                            best = Math.Max(best, minimax(depth + 1, !isMax));
                            gameBoard[boardIndex] = 0;
                        }
                    }
                    else { break; }
                }
                return best;
            }
            // minimizer's move (Player = 1)
            else
            {
                // get utility score for current board-state
                int score = utility();
                if (depth == 0)
                {
                    return -score;
                }
                int best = 1000;

                // iterate through all moves, recursive call to minimax, then undo all moves
                for (int boardIndex = 0; boardIndex < gameBoard.Length; ++boardIndex)
                {
                    if (depth != 15 || victoryCheck() != 1)
                    {
                        if (gameBoard[boardIndex] == 0)
                        {
                            gameBoard[boardIndex] = 1;
                            best = Math.Min(best, minimax(depth + 1, !isMax));
                            gameBoard[boardIndex] = 0;
                        }
                    }
                    else { break; }
                }
                return best;
            }
        }

        // returns best AI move (<24 lines)
        public int computerMove()
        {
            int bestMoveScore = -100;
            int bestMoveIndex = 0;
            int depth = 0;
            for (int boardIndex = 0; boardIndex < gameBoard.Length; ++boardIndex)
            {
                if (gameBoard[boardIndex] == 0)
                {

                    // make AI move
                    gameBoard[boardIndex] = -1;
                    int score = minimax(depth, false);

                    // undo AI move
                    gameBoard[boardIndex] = 0;

                    // get best score
                    if (score > bestMoveScore)
                    {
                        bestMoveScore = score;
                        bestMoveIndex = boardIndex;
                    }
                }
            }
            return bestMoveIndex;
        }

    }
}
