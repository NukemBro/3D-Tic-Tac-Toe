using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GUI2
{
    public class Score
    {
        public string name = string.Empty;
        public int wins = 0;

        // constructor ( <24 lines)
        public Score(string n, int w)
        {
            name = n;
            wins = w;
        }

        // increments score ( <24 lines)
        public void Increment()
        {
            wins++;
        }

        // comparison function ( <24 lines)
        public int CompareTo(Score s)
        {
            if (this.wins == s.wins)
            {
                return 0;
            }

            if (this.wins > s.wins)
            {
                return 1;
            }
            return -1;
        }

        // formating for label ( <24 lines)
        override
        public string ToString()
        {
            string s = "";
            if (!(name.Equals("null") || name.Equals(("Not a score"))))
            {
                s = name + "    " + wins;
            }
            return s;
        }

    }
}
