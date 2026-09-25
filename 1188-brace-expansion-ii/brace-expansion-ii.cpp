#include <vector>
#include <string>
#include <set>

using namespace std;

class Solution {
public:
    vector<string> braceExpansionII(string expression) {
        int i = 0;
        set<string> result = parse(expression, i);
        return vector<string>(result.begin(), result.end());
    }

private:
    set<string> parse(const string& s, int& i) {
        set<string> current;
        set<string> term;
        term.insert(""); // Start with an empty string for concatenation

        while (i < s.length() && s[i] != '}') {
            if (s[i] == ',') {
                // Union the current term into the result set and reset the term
                for (const string& t : term) {
                    current.insert(t);
                }
                term = {""};
                i++;
            } else if (s[i] == '{') {
                i++; // Skip '{'
                set<string> sub = parse(s, i);
                // Concatenate current term with the parsed sub-expression set
                set<string> next_term;
                for (const string& t : term) {
                    for (const string& sb : sub) {
                        next_term.insert(t + sb);
                    }
                }
                term = next_term;
            } else {
                // Single letter
                string letter(1, s[i]);
                i++;
                set<string> next_term;
                for (const string& t : term) {
                    next_term.insert(t + letter);
                }
                term = next_term;
            }
        }

        if (i < s.length() && s[i] == '}') {
            i++; // Skip '}'
        }

        // Add any remaining terms to the current result
        for (const string& t : term) {
            current.insert(t);
        }

        return current;
    }
};