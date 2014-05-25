package igblastwrp.blast

import igblastwrp.Util

/**
 Copyright 2014 Mikhail Shugay (mikhail.shugay@gmail.com)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
class Clonotype {
    final String vSegment, dSegment, jSegment
    final int cdr1start, cdr1end, cdr2start, cdr2end, cdr3start, cdr3end

    Clonotype(String vSegment, String dSegment, String jSegment,
              int cdr1start, int cdr1end, int cdr2start, int cdr2end, int cdr3start, int cdr3end) {
        this.vSegment = vSegment
        this.dSegment = dSegment
        this.jSegment = jSegment
        this.cdr1start = cdr1start
        this.cdr1end = cdr1end
        this.cdr2start = cdr2start
        this.cdr2end = cdr2end
        this.cdr3start = cdr3start
        this.cdr3end = cdr3end
    }

    String generateEntry(String seq, String qual) {
        def cdr1nt = cdr1start >= 0 ? seq.substring(cdr1start, cdr1end) : "N/A",
            cdr2nt = cdr2start >= 0 ? seq.substring(cdr2start, cdr2end) : "N/A",
            cdr3nt = cdr3start >= 0 ? seq.substring(cdr3start, cdr3end) : "N/A"

        def cdr1q = cdr1start >= 0 && qual ? qual.substring(cdr1start, cdr1end) : "N/A",
            cdr2q = cdr2start >= 0 && qual ? qual.substring(cdr2start, cdr2end) : "N/A",
            cdr3q = cdr3start >= 0 && qual ? qual.substring(cdr3start, cdr3end) : "N/A"

        def cdr1aa = cdr1start >= 0 ? Util.translate(cdr1nt) : "N/A",
            cdr2aa = cdr2start >= 0 ? Util.translate(cdr2nt) : "N/A",
            cdr3aa = cdr3start >= 0 ? Util.translate(cdr3nt) : "N/A"

        [vSegment, dSegment, jSegment, cdr1nt, cdr2nt, cdr3nt, cdr1q, cdr2q, cdr3q, cdr1aa, cdr2aa, cdr3aa].join("\t")
    }


    final
    static HEADER = "v_segment\td_segment\tj_segment\tcdr1nt\tcdr2nt\tcdr3nt\tcdr1q\tcdr2q\tcdr3q\tcdr1aa\tcdr2aa\tcdr3aa",
           HEADER_RAW = "v_segment\td_segment\tj_segment\tcdr1start\tcdr1end\tcdr2start\tcdr2end\tcdr3start\tcdr3end"


    @Override
    String toString() {
        [vSegment, dSegment, jSegment,
         cdr1start, cdr1end,
         cdr2start, cdr2end,
         cdr3start, cdr3end].join("\t")
    }
}