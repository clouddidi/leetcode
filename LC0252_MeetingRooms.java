/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
@TagAnnotation(tags={"Other"})
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, (Interval i1, Interval i2) -> {
                return i1.start - i2.start;
            }
        );
        
        int end = intervals[0].end;
        // this already takes care of the cases where i1.start==i2.start
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < end)
                return false;
            end = intervals[i].end;
        }
        return true;
    }
}