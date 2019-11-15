using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace GUI2
{
    public class ScoreCalc
    {
        // list of scores
        public List<Score> scores = new List<Score>();

        // holds currentPlayer
        public string currentPlayer;

        // constructor (<24 lines)
        public ScoreCalc()
        {
            retrieveScores();
        }

        // retrieves scores ( <24 lines)
        public void retrieveScores()
        {
            try
            {
                // file I/O
                FileInfo fInfo = new FileInfo("Scores.txt");
                string[] lines = System.IO.File.ReadAllLines("Scores.txt");

                // File validation
                if (!fInfo.Exists || fInfo.Length==0)
                {
                    Console.WriteLine("no scores file found");
                }
                else
                {
                    foreach (string s in lines)
                    {
                        string[] ssize = s.Split(new char[0]);
                        try
                        {
                            Score Sc = new Score(ssize[0], Int32.Parse(ssize[1]));
                            scores.Add(Sc);
                        }
                        catch (System.FormatException)
                        {
                            Console.WriteLine("{0} caused an error, {1}", ssize, ssize.Length);
                        }
                    }
                    scores.Sort((x, y) => -x.CompareTo(y));
                }

            }
            catch (FileNotFoundException e)
            {
                Console.WriteLine("file not found in retired");
            }
        }

        // adds player initials ( <24 lines)
        public void AddPlayerInitails(string s)
        {
            currentPlayer = s;
            Boolean firstTime = true;
            foreach (Score Sc in scores)
            {
                if (Sc.name.Equals(s))
                {
                    firstTime = false;
                }
            }
            if (firstTime)
            {
                scores.Add(new Score(s, 0));
            }
        }

        // gets player's score ( <24 lines)
        public string GetPlayerScore(string initial)
        {
            foreach(Score Sc in scores)
            {
                if(Sc.name == initial)
                {
                    return "" + Sc.wins;
                }
            }
            return "";
        }

        // gets n'th score ( <24 lines)
        public string GetNthScore(int index)
        {
            if (scores.Count > index)
            {
                return scores[index].ToString();
            }
            return "";

        }

        // increments the score ( <24 lines)
        public void IncrementScore(string name)
        {
            foreach(Score Sc in scores)
            {
                if (Sc.name.Equals(name))
                {
                    Sc.Increment();
                }
            }
            scores.Sort((x, y) => -x.CompareTo(y));
        }

        // writes scores to file ( <24 lines)
        public void WriteScores()
        {
            StreamWriter file = new StreamWriter("Scores.txt");
            foreach (Score Sc in scores)
            {
                Console.WriteLine("SC: {0}", Sc.ToString());
                file.WriteLine("{0} {1}", Sc.name, Sc.wins);
            }

            file.Close();
        }

    }
}
