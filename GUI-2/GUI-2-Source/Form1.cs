using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Media;

namespace GUI2
{
    public partial class Form1 : Form
    {
        System.Windows.Forms.Button[] boardButtons = new System.Windows.Forms.Button[64];
        backend be = new backend();
        ScoreCalc calc = new ScoreCalc();
        int[] wins = { };

        // constructor ( <24 lines)
        public Form1()
        {
            InitializeComponent();
        }

        // loads the form ( <24 lines)
        private void Form1_Load(object sender, EventArgs e)
        {
            map_buttons();
            timer1.Enabled = true;
            timer1.Start();
        }

        // maps GUI board buttons to an array of buttons (<24 lines)
        private void map_buttons()
        {
            boardButtons[0] = game_board_button0; boardButtons[1] = game_board_button1; boardButtons[2] = game_board_button2; boardButtons[3] = game_board_button3;
            boardButtons[4] = game_board_button4; boardButtons[5] = game_board_button5; boardButtons[6] = game_board_button6; boardButtons[7] = game_board_button7;
            boardButtons[8] = game_board_button8; boardButtons[9] = game_board_button9; boardButtons[10] = game_board_button10; boardButtons[11] = game_board_button11;
            boardButtons[12] = game_board_button12; boardButtons[13] = game_board_button13; boardButtons[14] = game_board_button14; boardButtons[15] = game_board_button15;
            boardButtons[16] = game_board_button16; boardButtons[17] = game_board_button17; boardButtons[18] = game_board_button18; boardButtons[19] = game_board_button19;
            boardButtons[20] = game_board_button20; boardButtons[21] = game_board_button21; boardButtons[22] = game_board_button22; boardButtons[23] = game_board_button23;
            boardButtons[24] = game_board_button24; boardButtons[25] = game_board_button25; boardButtons[26] = game_board_button26; boardButtons[27] = game_board_button27;
            boardButtons[28] = game_board_button28; boardButtons[29] = game_board_button29; boardButtons[30] = game_board_button30; boardButtons[31] = game_board_button31;
            boardButtons[32] = game_board_button32; boardButtons[33] = game_board_button33; boardButtons[34] = game_board_button34; boardButtons[35] = game_board_button35;
            boardButtons[36] = game_board_button36; boardButtons[37] = game_board_button37; boardButtons[38] = game_board_button38; boardButtons[39] = game_board_button39;
            boardButtons[40] = game_board_button40; boardButtons[41] = game_board_button41; boardButtons[42] = game_board_button42; boardButtons[43] = game_board_button43;
            boardButtons[44] = game_board_button44; boardButtons[45] = game_board_button45; boardButtons[46] = game_board_button46; boardButtons[47] = game_board_button47;
            boardButtons[48] = game_board_button48; boardButtons[49] = game_board_button49; boardButtons[50] = game_board_button50; boardButtons[51] = game_board_button51;
            boardButtons[52] = game_board_button52; boardButtons[53] = game_board_button53; boardButtons[54] = game_board_button54; boardButtons[55] = game_board_button55;
            boardButtons[56] = game_board_button56; boardButtons[57] = game_board_button57; boardButtons[58] = game_board_button58; boardButtons[59] = game_board_button59;
            boardButtons[60] = game_board_button60; boardButtons[61] = game_board_button61; boardButtons[62] = game_board_button62; boardButtons[63] = game_board_button63;
        }

        // event handler for textfield ( <24 lines)
        private void initials_tb_TextChanged(object sender, EventArgs e)
        {
            splash_start_btn.Enabled = true;
        }

        // event handler to go to game page ( <24 lines)
        private void start_game_btn_Click(object sender, EventArgs e)
        {
            calc.AddPlayerInitails(splash_intials_textbox.Text);
            label2.Text = "1) " + calc.GetNthScore(0);
            label3.Text = "2) " + calc.GetNthScore(1);
            label4.Text = "3) " + calc.GetNthScore(2);
            label5.Text = "4) " + calc.GetNthScore(3);
            label6.Text = "5) " + calc.GetNthScore(4);
            label8.Text = "" + calc.GetPlayerScore(calc.currentPlayer);
            GUI2_tb_ctrl.SelectTab(game_board_tpage);
            label1.BackColor = Color.FromArgb(255, 0, 255, 0); label2.BackColor = Color.FromArgb(255, 0, 255, 0);
            label3.BackColor = Color.FromArgb(255, 0, 255, 0); label4.BackColor = Color.FromArgb(255, 0, 255, 0);
            label5.BackColor = Color.FromArgb(255, 0, 255, 0); label6.BackColor = Color.FromArgb(255, 0, 255, 0);
            label7.BackColor = Color.FromArgb(255, 0, 255, 0); label8.BackColor = Color.FromArgb(255, 0, 255, 0);
            score1Label.BackColor = Color.FromArgb(255, 0, 255, 0);
            score2Label.BackColor = Color.FromArgb(255, 0, 255, 0);
            score3Label.BackColor = Color.FromArgb(255, 0, 255, 0);
            score4Label.BackColor = Color.FromArgb(255, 0, 255, 0);
            score5Label.BackColor = Color.FromArgb(255, 0, 255, 0);
            gamepageInstruct1.BackColor = Color.FromArgb(255, 0, 255, 0);
            gamepageInstruct2.BackColor = Color.FromArgb(255, 0, 255, 0);
            gamepageInstruct3.BackColor = Color.FromArgb(255, 0, 255, 0);
        }

        // event handler to go to instructions page ( <24 lines)
        private void instructions_btn_Click(object sender, EventArgs e)
        {
            GUI2_tb_ctrl.SelectTab(instruct_tpage);
        }

        // event handler to go to main page ( <24 lines)
        private void main_menu_btn_Click(object sender, EventArgs e)
        {
            GUI2_tb_ctrl.SelectTab(main_tpage);
        }

        // timer to blink winning cells (<24 lines)
        private void timer1_Tick(object sender, EventArgs e)
        {
            // go through win vector and make each cell blink
            for (int winCellIndex = 0; winCellIndex < wins.Length; winCellIndex++)
            {
                if (boardButtons[wins[winCellIndex]].BackColor == Color.FromArgb(0, 255, 0, 0))
                {
                    boardButtons[wins[winCellIndex]].BackColor = Color.FromArgb(128, 255, 0, 0);
                }
                else
                {
                    boardButtons[wins[winCellIndex]].BackColor = Color.FromArgb(0, 255, 0, 0);
                }
            }
        }

        // event handler for all board buttons (<24 lines)
        private void game_board_button_Click(object sender, EventArgs e)
        {
            // get the button that was pressed
            Button button = sender as Button;

            // check if button pressed was a valid move
            bool isValidMove = be.makeMove(button.TabIndex - 15, 1);

            // frontend and backend check for valid move
            if (isValidMove && button.Text.Equals("\r\n"))
            {
                button.Text = "X";
                int ai_move = be.computerMove();
                be.makeMove(ai_move, -1);
                boardButtons[ai_move].Text = "O";
                boardButtons[ai_move].ForeColor = System.Drawing.Color.Red;
                winCheck();
            }
        }

        //// "//yes" button event handler (starts a new game) (<24 lines)
        private void game_board_yes_Click(object sender, EventArgs e)
        {
            show_endgame_dialogue(false);
            be.clearGameBoard();
            clearBoard();
            enable_all_buttons(true);
        }

        // "no" button event handler (exits the application) (<24 lines)
        private void game_board_no_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        /* left this function larger than 24 lines because creating further functions
           to save space would depreciate the readibility of this function */
        // checks for victory
        private void winCheck()
        {
            int isWin = be.victoryCheck();
            if (isWin == 1 || isWin == -1)
            {
                // player win logic
                
                if (isWin == 1)
                {
                    Console.Write("Win\n");
                    calc.IncrementScore(calc.currentPlayer);
                }

                calc.WriteScores();

                label2.Text = "1) " + calc.GetNthScore(0);
                label3.Text = "2) " + calc.GetNthScore(1);
                label4.Text = "3) " + calc.GetNthScore(2);
                label5.Text = "4) " + calc.GetNthScore(3);
                label6.Text = "5) " + calc.GetNthScore(4);
                label8.Text = "" + calc.GetPlayerScore(calc.currentPlayer);

                Console.WriteLine(label2.Text);
                Console.WriteLine(label3.Text);
                Console.WriteLine(label4.Text);
                Console.WriteLine(label5.Text);
                Console.WriteLine(label6.Text);
                Console.WriteLine(label8.Text);

                // disable/hide buttons
                enable_all_occupied_buttons(false);
                show_endgame_dialogue(true);
                wins = be.getVictoryMoves();
            }
        }

        // enables and shows end-game yes/no dialogue (<24 lines)
        private void show_endgame_dialogue(bool show)
        {
            // toggles "continue" label
            game_board_continue.Visible = show;
            game_board_continue.Enabled = show;

            // toggles "yes" button
            game_board_yes.Visible = show;
            game_board_yes.Enabled = show;

            // toggles "no" button
            game_board_no.Visible = show;
            game_board_no.Enabled = show;
        }

        // cleans GUI game board (<24 lines)
        private void clearBoard()
        {
            for (int buttonIndex = 0; buttonIndex < boardButtons.Length; buttonIndex++)
            {
                boardButtons[buttonIndex].Text = "\r\n";
                boardButtons[buttonIndex].ForeColor = System.Drawing.Color.Lime;
                boardButtons[buttonIndex].BackColor = System.Drawing.Color.FromArgb(35, 35, 35);
            }
            wins = new int[] { };
        }

        // toggles all gameBoard buttons or buttons that already have peices (<24 lines)
        private void enable_all_buttons(bool isDisabled)
        {
            for (int buttonIndex = 0; buttonIndex < boardButtons.Length; buttonIndex++)
            {
                boardButtons[buttonIndex].Enabled = isDisabled;
            }
        }

        // toggles all gameBoard buttons that are empty (<24 lines)
        private void enable_all_occupied_buttons(bool isDisabled)
        {
            for (int buttonIndex = 0; buttonIndex < boardButtons.Length; buttonIndex++)
            {
                if (boardButtons[buttonIndex].Text.Equals("\r\n"))
                {
                    boardButtons[buttonIndex].Enabled = isDisabled;
                }
            }
        }

    }
}
