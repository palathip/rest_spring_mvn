package com.rest_spring_mvn.application_pgp;

import org.bouncycastle.openpgp.PGPException;
import org.bouncycastle.openpgp.PGPSecretKeyRing;
import org.bouncycastle.util.io.Streams;
import org.pgpainless.PGPainless;
import org.pgpainless.decryption_verification.ConsumerOptions;
import org.pgpainless.decryption_verification.DecryptionStream;
import org.pgpainless.key.protection.SecretKeyRingProtector;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class PgpService {

    public String pgpDecrypt() throws IOException, PGPException {
    String input = "-----BEGIN PGP MESSAGE-----\n" +
            "Version: OpenPGP v2.0.8\n" +
            "Comment: https://sela.io/pgp/\n" +
            "\n" +
            "wcBMAwiXxe7zcZA+AQf9HP0zzTaCG9Fw+RqdvqbHMgHZjHBJ2RZ1wlPnD2Az8Qub\n" +
            "nxyvOo5s8GCxAOncBMH1N8MBTL0YznM9QCPvP3TAV0S3md6Bpy/VvIJjgUV4LmL9\n" +
            "ZeiqyXx1vTowbENLRNI2SX5fpH56psQwt1YpDymE+dR7dRDyZ8TxyZfhOPqorPUM\n" +
            "LBRYxV64K8/dlBqMPo4JL1A/G+/NO1v2QCIAcPSEELA/54yoO6grdaOMTIXtNOrh\n" +
            "y1wYd78rJoLZzf9BNlsCk3C05xDMpdVC5t2nW3e+smz2CA/XjSkYgtUwFT4ZdcS8\n" +
            "yNzxEVCJTQDOl6qU4KxkRZMjp6x22tAbuHehxAxjTNLAwwFpi6FyBf8B96Il+wzo\n" +
            "O5rZFmrM/IGHIhV4D6OCN0jh1RbQV9aFwmGq9eYvB2QeDyFQb8gZOp3pu7SY0j5t\n" +
            "wH2CibVPw7WtA19b8cZG0D8mIvOTup0qRUh+Thp3PUclTdhQ8+rTKJ0Pi0VnmCl5\n" +
            "dj7gevzeOXWrlTIJ3WY2Va82MEc82vRMVqei9eRFtZjE1wPVi+h3OjvIIIgmvW6R\n" +
            "U3LeAFUofUBdRkZHzf8H/eyE7ws0ZiRJoRGlTGkwCx6CeCNqrmaQCw3xdrJyTSR3\n" +
            "AFW36h229f0k79Gcf1SnD1vd3WmZFhUvALJvcsIDAwVPM2sp7K0OOVVv1L6RKrt0\n" +
            "LoiFdk2XHTxgeNrSctmr9NLA9hszkKJHnaDuVFNg5q4N2gMAeAzrGIFsh9JUy5YZ\n" +
            "DK2edGLd/ZdKfFbZbbzp5kJGVMQ0FpKx6mbpY2jNiimfzXCEeVIHlTokcZDOH9Ic\n" +
            "k3Ag+GDxL3Rx8Rs3wpRfqzbxig0qdAAkWelznKPiXb0TMjcOQA==\n" +
            "=kIj8\n" +
            "-----END PGP MESSAGE-----\n";

    String key = "-----BEGIN PGP PRIVATE KEY BLOCK-----\n" +
            "\n" +
            "xcaGBGJEHooBEADK5polofPSuaGWsAXjWq5wNZG6o3CmAViCSFtyDwTdpcQepn/J\n" +
            "D4pms+CBVHb9F1wPpbaDbA36yCid60XjAOQb4on6DvKZ5N+VvCDhPDrEaxP7nG1t\n" +
            "MXXzV9xsJtQn7ageknNyX9km2/4Q2UXdy0G352sLk8PxmtblzFpP1m8nHNr3L7Lr\n" +
            "YUzetQk+TSh98u7+9oOyXG3uluSFenS5UT1kPiEp0uS3JqmYjQzjG65+pEQ4FOPr\n" +
            "rIWcYuP4hwM0mLgcJLIScWHn6fwMJnb5viJ363rtVEIjlJ4OBC/eqN8yJC63NoCy\n" +
            "9eqlYKDNytsICNaMbtjZbkl3xPYEiw2TBTvYe6fIIYVXScZmie44YsddWrGMWV0b\n" +
            "uvzBgkWDwlj/Bkv3WRH/hGbzSk2Z8eJ3x2l+h7laEBdgVBE/RYfSWCyo1Daw+qcq\n" +
            "JujcS5+W9013vVywdsvc6UsKMg5jVt/SzqukfBkw4xwM0M0vSrw2jDgOSzLvu2xK\n" +
            "/Ux2ubeL8Hxbr2ZtKTIDBX/PVDkkRXtMEEcRtoXqGaR4tuJvJqiUR/aFBBeaIoxM\n" +
            "hAyb0Ye9VOfwXKm6TDihSL+v5XtYfL9VJsT5M76tgIReULiepgeCx35xi7msibg6\n" +
            "zq19z7EgZ+16V1G5HjQGg31sueo1CtIJoB2txlSznCEUeVsrLkT7OYEVdwARAQAB\n" +
            "/gkDCEtF0bqfIxjKYMhg9ow9PufzlALTUpieIb2XfPNkDHhJNmeUABTg8FJ7Vstj\n" +
            "/0lGS+RnQyjrOsr/RbdOVwq1iebHdi3bc+bHyUFv8/lEBl/cFraGhkuMpF/+qXtd\n" +
            "rmMVWJaTXEJYnCfUe6R6EXo7SzPSkmGB42QYJH1RX1AC2etN7/bntQL7w3QQKdwd\n" +
            "AbjA9cAGv8+b5tSaJpt4Kkgl/FRgTSseijK7laqQExq4VnbtouBmP0MfTy5ipeBV\n" +
            "kN6VcSrKKoQt2fHRqXiBcCFbSYSkNuoQ6hwcKv73Kai6hJdWmrp8AYUvNMRS3UE+\n" +
            "mGVUcF3+7DhQ+isXyR0u8hS2mm252gAolNo7W4V6A/cMOuzVf7crL6dv9XJiXNPO\n" +
            "YK6dGlNxpSOmp2Y2XtiPDUx9oDiP+QQC1pYdwrXmcksQDjjBusj8ftwJb36wTvMd\n" +
            "nVwv/pAbMtt+aaX7eqq5HtzRPRSGEWFGIcvJ2Ud4KLAx+mwBqA5JPNlmPm+hTIBc\n" +
            "tE95pMQnUo5wuNg7UqplqvX48a3wqVe0ojS+JRjy4k5ky6G2+yf/Z/Eiptxko9Pu\n" +
            "YN0cqtUU+koG15RXOBYWp9YGwwdUVOWmPx2xkEkOT4sq0/A2vil5PacmIVWIBZil\n" +
            "IL3oA5lwCkHi2nbhlQ2LTSsnpl3b7B0l4EUca/qPZRhgGgoIzMOAi32LL6nb7b7/\n" +
            "5/DHBcxqb74VmFEQBkY66yUFLIrapBhA58iqXdoFzeh5RSnQDDte6ZnOExv8npli\n" +
            "T7S+MCI64Ge2jKt9HVQ0UALe1wgl0Ln0BefnD54EQLaZ+2mnLMtsEWO3eLPtL1oI\n" +
            "j9pMY4LrJFlmiDHPawd6Es+kvR+TBWsecDyom1o+vMjiGvbCW2vGKuQ94ShhtsQj\n" +
            "bNuAWWk+QVxIVIrBO63Xi6ZdVxuLoA5k33ShBGqnIiwYqu76/MU4TgpV3I0Iw2Rb\n" +
            "UBN0iBOGj2jcQDH5kBrSp/rA8DuNLQxN6jdCC8TJ4C5e250d72vkLUoLyacOKk/p\n" +
            "R47E5w5cF4LWu4q2ZEeANVuxvlQUQ4ABA9hBhrpzzKXYCZpcSAyyfhGj8jEV146I\n" +
            "d7f6kRJDoSWdgtkVZBVaJFWnZRYycbhAmGxcxpCquAbLlmLCC6Vsvf3PWgmLwPYs\n" +
            "XMvuF6QDj5DEIm8rJNh6HySmHNuZDyeSl0uWJg/fzBDhpETEq1IeYeDcrv3F5Ou5\n" +
            "/S77NxEcgDfYzMZ3767eBMCNMHGsz0tzHMQLxDGp4xbq3U1jwZ4kLM9N3m83KOIZ\n" +
            "RKP4avRfgVArXZ8sDppy1WKqpuhfifvypV78EY0IJhOpGlas1Dnu+vQnmfk6GJwU\n" +
            "I5IyST/F2K+kENicuSatqoPMk4xWPJYqhh/syKICtBb+iijlajn5xApZdM7bqJ4O\n" +
            "504Y/pNB5hKinohtG68bFTHHz5yYSqaeymAgk9zh/qIy8FYirjg8z2XCDiyTMSTE\n" +
            "C1M/rCIcBQQ6YYKN0nQ3xbwaGDucFdGhrcBUnEI66hqra5nOoQG1GMcoqHEKMS/Y\n" +
            "tOmorvui5eNzL7SlVjVxDr5Eseo0yg2wPTX4Rk2pfiEuv/gxk4uWyQikFpoJjwOW\n" +
            "fvFJO8HD6sFP7J2rGY3a5+kpjhq3G7NOPHvppKmIySUQkUWzw42clJzT6okkDV9u\n" +
            "eti1eNCGUfYAUpB/0M2Ak/FeNmhgjsxV7Y0AniHfxK2EdaOUSHvgPsqhQWLjfS1T\n" +
            "P/+zKGW8gIKBCTXYcr8RmJdoG1rJRNhYrtqK9qow0ETN0jWKnX0XKfzNF2Jvb20u\n" +
            "cGFsYXRoaXBAZ21haWwuY29twsFwBBMBCgAaBQJiRB6KAhsvAwsJBwMVCggCHgEC\n" +
            "F4ACGQEACgkQzZMehskGXkLIuw//Zzt59R8gxLJGdUVCuLmmordlVFjzZ6RLgEAW\n" +
            "Yy7h9PXuJAr67XBlmnCpv2jwncMOAbGDgAf/iWIu5o/oRAmwwoGZTPZKRpzoW2Ek\n" +
            "9/bIfDF0ZjSexey3uyz+Fm1RseoymvvOtC7qrzjUWcC4QopPq1DeGGQq+AE2bo0y\n" +
            "AgE/jeTbVTHUJnlxDIw84Vf3vcLF50uPV6jmhazYMK9JbA3v6GXg+1cBX83t7QXe\n" +
            "jCuH6nhTOgK2+IKxyBXWDW/HED3WOscFokGFlACb+e0We3Opfqst8fA0SCy/VrTu\n" +
            "zcvaxZI61GnAYqKGCcSGoqh+iMGkiEWRC8ck1jfIdAa37Nga2LmZm8iSlCVyIVOf\n" +
            "IN1ml+iuPwVFqkDPAyx/U7OCIHKIgpiyLN4tJsGNRLGcoC8F3unrf1VHWF9WmBmw\n" +
            "FLB9h7J7VF4dMjSlIxyDpkSAajMEdDENsXKv9OGjfOZzvj5XM5fItuFn1FMuPbyu\n" +
            "/UV7c8d50ipVdPlMvypQYVRVY4IP06+DUD2jlkXHKPDMuIvhG6xf87G+r9E1zoVN\n" +
            "f/kSWKulPCbEFBtDUY8wTfG27uTB+Sq896uxmK/AzAwkm8YvPeaqkRecpVL0iIDR\n" +
            "FpxYO8FfXmSZT+EwGk6vzoYrIupeBMloQZgKymHXDERwdc6KPENo1SCFMPuyRVEI\n" +
            "ecTacjXHwwYEYkQeigEIAKP6kWxDmjK0jyKVk15PQJyYuJdI499JuHBMnmOkxPAO\n" +
            "N0dBW+EqtBgNMD04FpZK4U+Dcc8H3dP88/HtCWqOL7kZjDz5FqjMCFHQfjA2FS7P\n" +
            "VEG1Y1QxNXC8AOF1qJgnMq6x/CI6E1XE7/Pozn6l715Dt24BOlTOhRCcBv7/DLe8\n" +
            "WERAJkO1wSNtF9TJHyFYjRUv+s0iboIfZtVDWg22aAyQnxdnt5K3AlUWl9uqi61t\n" +
            "K92rFRHbhsqKPdin4dEWmAgVqZWyonLgeKSCXAVRR03WcRZXtmAtXdVEIxn8BOQ6\n" +
            "j8p9grrRlbs6fNIKmKSb6d1+r+jd2x3BlrYJUwdI5H0AEQEAAf4JAwg+r8ezWgS6\n" +
            "UWB2x3rUf8wLr18/XHHHqUPXNAFPiDPHx1eAqW/8YmdPlDDOVySL8iqLHMiBkOh0\n" +
            "s04R3lW23VRAwN+fPOopJvEgkaDtjEUc3zxJAw/4Y+c+pWhUQp/4Nt0Mz88I6h+Z\n" +
            "DAbAkRyQK38rBEuUiiTZ1A5fLelOSc41fqJTxEM47FzlNWU224qXOSzxknX2bONw\n" +
            "TPBxyga/cpHt4H6SfkVOG4yosEHXn9Xlc204kAzWnMBNj+8gMIgMSkttaxqzWqUb\n" +
            "+5xHS3tVOhOnpeHdk8jB/Z24ZdDCQ28wSals5gkPhqFz6Gi+3u6C0VbT//JLJ3Sh\n" +
            "8QbLgQr/kDkaU5hbFLExmND0qvh/4KwLf1tMYWcO28MpZSlkvq5bX4HIxwBzeXJf\n" +
            "yYOTCJnof++S5mxcJUIiBFwz4vbRPfrdrFdOO+iDVERK2XDkSuLz3BEzs2/JTwD1\n" +
            "IefRtzKSeyzWYPdVCa8tBaXkvASq53dzRCw27mx/Z/FLYMSgAnPo0+0+zN+bpvTF\n" +
            "IIDi0Aig4t6Jwva2N8eycCl4pq2G6yz9m4I7Bmb81Dd93V00k9aZxlO6Vl2wUh21\n" +
            "F3e2LRlaaQAKGy11m4BkBVdNy5WZ8xZJBPAANuh4YZfqGIrzDBO0nuka7faNejXB\n" +
            "cliMYgFADZo+buqgKXb3XpSDeJri6ydUQqoHamkriTguGGBkkurkCEAnqThrpTbV\n" +
            "l0oK/QuKw31rnXGsgRBT3Ps3iqFmv6z/2D37jAas4hEU4A+6bTya0r03/Q/8rxZp\n" +
            "1zLcapY3xK2/KwZBZhyCAePz/bhxOv4Wl3WOQGWHUrZqsgAWigIE61jWiN4Nombq\n" +
            "BBSkySYnup2VESLT2jLKQ3wdpMtFkyfEqRVGQH0NFfCgXVA6XsTDE50rV9LrpYax\n" +
            "LxnTvQp6KcLoES0PmkDCwoQEGAEKAA8FAmJEHooFCQ8JnAACGy4BKQkQzZMehskG\n" +
            "XkLAXSAEGQEKAAYFAmJEHooACgkQCJfF7vNxkD6BvQf9GsF2/t1HPmSjZ+cjUhfd\n" +
            "RuBBkJV4A/uC/M3dPEh+3E4+m4qMK4MYfP2heiyEzTazWl1gyAiouzxSgoFF4Dye\n" +
            "xTYHMrAL3F7MEJiKED9DptJkYoZ5/e2ZrLrMJiiznqcxcSczWzHhwhtIx/jxKr9I\n" +
            "+nYYSjMT00gcWYowZfcGfP79r2zDHQfuVwLJ7j8EmD+6VrA2tvkseXVXJlRW69/c\n" +
            "D46vM9x4IThfhjGc92Aa4DZaVz7MQLLRW+cRONznjUV91EXRyPgkIPdazQH5DfOu\n" +
            "gPgi8Lbi4Yi1060QBB0McOU+Oo/HPxoy8H2BufH3ZQrGXxT4qa01o2x3W8aK09mE\n" +
            "pQG5EACqNPg1qeHx8pykUvh4I7Fp+YHbJ+3x613pezbS8hp/Umr0kDv/4ZklljFr\n" +
            "aHZ85L7we15Kg9/A04gotHEVs7zJHFxRODR/BEILBnNUoVNEHEz/8SnhoKNXGeDE\n" +
            "4FArsCkl8aixcjYUJyxSmPataECvKIbN6ZaesrxWiMNifkAYwJBEqA80ovaH/WH0\n" +
            "6ACCDyDVnBvQL61AWoNs1t9q/fAdMKkUCxuPQlxAhcK1f0NSMN8c9dO86JRVkDba\n" +
            "CLduqwtO9oXjcgHNJY/VfDhp+GZlUW4BGBcmDO7QoQNZGeIFMSF9NnR532AQ87Vj\n" +
            "kwMWr46spqRlfJV3r8zLCmLs9SQkcaPs4xe6dbBqP8LNaEbL6I09vW3FUmklr1JP\n" +
            "ATuCshBFH/0997xHFYiB+eH1uAdeqgdiyVkEyD3tfp3dZ0OD8zi8u5qa4mbEsd9J\n" +
            "rjM6/W0fkB8QZcbN3n/vIHslU1FEkkrX2Lm95hNeCrfEnl860zXLJ43Q8wuNfDwF\n" +
            "n734KuUQgPQUjnO8RRGnwuMmNcV9remLySiaRhqDggZ9pTrjMrpglbfRrjNC/doY\n" +
            "ciKy8BLdOCkYUiWSxpe6l8a6KA7fMiQvtNraAJsnlnhVI+opIYEvvAreI3cGedQ/\n" +
            "uETgj2yuP+53xV6KTXT8TJBvbjrsSw5MFt5y/TbRvmS55OFjr8fDBgRiRB6KAQgA\n" +
            "w/m/VIlL/nK+7sMi6XIBLXhOvhvmMHlGcvB+ryCaFRRh4vgoSO4vQ59dB5EugJuo\n" +
            "oxp6S5U8+Tvji/uqawBSzBaUEugWCnR+rrnWVAC6xpZhv2qWJEC1+9Sd5r2Jpfz6\n" +
            "cfwMlRGo749iMzIXqE+5xYtAaZCxgmyyFi0ejLSomT6M9qH1Xjzf+2ZVCvZV+265\n" +
            "0oOO+MA4Rwt9XcET+Z/BCJrVOu4jO8tY02ely/qjd0bFir3uJjRsFpxpmLTOyweu\n" +
            "Mlnmp5waCPtxqaRYZ8+XZraWLiR5axNI4BO+9Sfxwxe9CATQ1jFZd3q/IinqfL/9\n" +
            "2CaH4SmmDzmJSbUU9WRXyQARAQAB/gkDCHt09bQr75yOYINc79K8Lfx+5MDbDCtM\n" +
            "h451pPvc+YLKT+VS1HYGs9e4aOdVj5rC01CFFmZh7AiV+pW6kAp6VES4CSOQwpei\n" +
            "6566yjhEdNshRvqsEjPIJM+TgOtfUml4eE3Xoizvpk1vQl8m2Un2/cJ/5D0d9QqU\n" +
            "qgjzqlZn7w446Wn9pKdigCRvTzxVS4F2udIyHUXyKd9Y9vvmSEdf0n/KjF+8I4ZG\n" +
            "mXeYbfQzTvuDMh/KzGbKXE1XuTUImW9M/uXOD+SaaSaCsTEx9V8dXWXGCBYLLfUC\n" +
            "z0uR4qdz30wQK97y/lMvtiOG/KSu/DS3oF8NbbHDr9EwJzVtw9nbXT/30tJ7irbt\n" +
            "qPFmDGt22u6BuuDT5IvAAV1iQPGOqNCSblaDuehDve2KpRj1KreafG6u16eYIch+\n" +
            "7DfsTPkC8C0dp8BX/2z+IbxSOUrtR/AzlH4xrr4bQ+GyxEtZCoSgo1pByH6FfJah\n" +
            "vJa4YlpYfgeyvAyBMnkaE/06LlT/Xl4ctztUNnCIO3PZWgvbbfD4Hh3Yr/OwpyjO\n" +
            "1WgDWedx1ObRVtwWzSPJmgIcWAiz3zBQ38MkY/a3sGgtH3dWtSQJQmj/nrE75usX\n" +
            "T67PEvoYZBKrRUwR0NcDNBvwV//EfBzOUmSN/kq7gTq6+WzjAa7n1N2vg43sP6li\n" +
            "/kX5rVDR/m4QBuxVn2HIbgzDHQmmISGAorE4D4UZgDi0DwQDpsKftfn5H9s/ysxA\n" +
            "GiGUp4P/Hy6zRdcUJN42sb8hR9HR2Ex1NDLPJEWP5TTmjBAIwOcc7OHulFd0erNv\n" +
            "1aO0dkgEBGCoDGyUdR6U8sQuXBvruNynjl/zPegZC4lglfle+eXC72jKxKjxnz2i\n" +
            "SlmiFk2xZecfCT6DnSGhknsgJTsfFwkY3TaDhSrrhdnshJJQ5hRFTXSD6ravg8LC\n" +
            "hAQYAQoADwUCYkQeigUJDwmcAAIbLgEpCRDNkx6GyQZeQsBdIAQZAQoABgUCYkQe\n" +
            "igAKCRAYbRuT81r7VnZ+CACK3wq7WCSdEXdk2vRVOwoBvAedahbydUuGD84YrYXi\n" +
            "gucEZj21A/fSNLku0LqY3tH1XJfZcW85DW3bWW7vBOkIPwYZlRghQghYqC1wCIIT\n" +
            "NdVhmSo2vAnVLYtca3xWK+VLeJWilfVcFcrgdzK8BoNz13hAzXEh51BgNmtc2gOR\n" +
            "9YAEm0KX0HLvbDRhZuavsxV7EggAOKD71Nu8EUv+q7IKxDijZaVXJoZllRC7NHFU\n" +
            "wnZ0rrLqUd8Uxm3FzB41M90jhMwWIyK+ZtEVlyO/mvxDs7Bs5Ho6sWuheLg7Dfvq\n" +
            "JLUNI+8QydDVzQ0r2pVo5GZog9Pw+Wry7r7JH0HrL7wSCuoP+wU9/OZo0AwnkcNI\n" +
            "BG+qthPUU+J4eFH6FbuU1NRmBn7lpOhg+kEywgV+nepMGRTz0BiOauu8ziJUg3BM\n" +
            "vPN7K2VQsRIdvK+A3VgDf0lSUYV/HPubXGmVsF2ltKXVGWmE8AU/2uStSeJz7PFO\n" +
            "kQMPJV6GrdynhOTyoZl5K9iebuj83BYCCQj4dp/bCbhE/FKdN5SB8WMSIe3aMk/3\n" +
            "8rgDtBylECJcImOpJ0K0jOoumI1ZyZ2OJWkAXvSEBs4IKJjSV+yfOxcUKUhejCar\n" +
            "nbsdGvFjN6FskBMHqP4uFivYcU2WRipKCzLEnTRQiRO6e+52jiiMJ47sRi/ZyZNP\n" +
            "49ahN71xqkM1sqmNM8HcWr/d/j1Hmhn4wLNsECUmISSWVmA/M+9KcvG2Lspl0KL2\n" +
            "ly3LoR18K8N0px6QdKmy47IeYywE5l0uDhV7wIZIf4Uryh+tAUo4GnT0H9Y/ED0Q\n" +
            "kB5xUaWOv+XOBfu+v7NHOOLkg8TqvqjN9PffvSgnr27fRWPtLgwoijEGVj05OJSA\n" +
            "lFiJ9rQDMVDeZGgJSL+9sLNmpic1N1AauHLa4RWVJxQbWqxh+PP8zd4cuC1ZD71o\n" +
            "k/8FFPwg4BN+Duo/8woVAUADLZ2Pq3ikOWcHQsBkIpBp/5LcpgwaRaJ3sMu+vmzb\n" +
            "Q+VomUPshs8T8vF8Gy8QkvqYrR5K\n" +
            "=GbAt\n" +
            "-----END PGP PRIVATE KEY BLOCK-----";

    SecretKeyRingProtector keyProtector = SecretKeyRingProtector.unprotectedKeys();
    ByteArrayInputStream encryptedInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
    PGPSecretKeyRing bobSecKeys = PGPainless.readKeyRing().secretKeyRing(key);

        System.out.println(input);
        System.out.println(key);
        System.out.println(keyProtector.hasPassphraseFor(12345678L));

    ByteArrayOutputStream plaintextOut = new ByteArrayOutputStream();

    DecryptionStream decryptionStream = PGPainless.decryptAndOrVerify()
            .onInputStream(encryptedInputStream)
            .withOptions(new ConsumerOptions()
                    .addDecryptionKey(bobSecKeys, keyProtector)
            );
        Streams.pipeAll(decryptionStream, plaintextOut);
        decryptionStream.close();

    // Result contains information like signature status etc.
    String metadata = plaintextOut.toString();

        return metadata;
}
}
