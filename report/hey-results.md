# jwt-only
Команда hey:
& "C:\WokStuff\ITMO\NIR-semester-2\loadgen\build\resources\main\hey\hey.exe" -z 180 -c 50 -m GET -H "Authorization: Bearer eyJraWQiOiJiYzNmMDc1ZS01YWE0LTQ4ZWUtOTdlYS0yMmUyY2RmZWQ1MTYiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhbGljZSIsImF1ZCI6ImRlbW8tY2xpZW50IiwibmJmIjoxNzgwMDcwNjY3LCJzY29wZSI6WyJhcGkucmVhZCJdLCJpc3MiOiJodHRwOi8vMTI3LjAuMC4xOjgwODAiLCJleHAiOjE3ODAwNzA5NjcsImlhdCI6MTc4MDA3MDY2NywianRpIjoiYmNhYzBjMmUtMDkwOS00MzVhLTk5YmUtOGJkYjZiM2NkOThlIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiIsIkZBQ1RPUl9QQVNTV09SRCJdfQ.MXm0AAOhmuQSeLvaCyP4vsq5nNVwPR0Voh-71DORX1rwu0DIr6Kg2IcvoQaK6IoqfpW0zF7cKXaDBXO61JLz3aOXNxAubHoXlb3y7b9gAIsnN6VIXNI68RpCcB9d-f8Gg2jmP8spvOSCwpyDx5hjhJvaIBZ96Ufh1zbs9mOubfLW7cUBRkIDVbVNtS0CbgDFv0A1yMbtuHlyCr3MfxMNJtXzlhqME9SjpXq8bX6mI7FWFiFfGeBCiD4Q6kS-ZQXhJkjPCPQ6IeN9aFl28NLD-0VYmmx8ioS6RP6sFoh3SThuSmtR35p072Zcr9zSHFm-WVlkngx6udqUfbKoTRtjwQ" "http://localhost:8081/echo?value=load1"


=== JWT ===
URL: http://localhost:8081/echo?value=load1
Throughput: 11689,96 req/s
Latency (avg): 9,00 ms
Latency (p50): 4,00 ms


Summary:
Total:	180.0033 secs
Slowest:	0.8661 secs
Fastest:	0.0007 secs
Average:	0.0090 secs
Requests/sec:	11689.9575

Total data:	10521155 bytes
Size/request:	10 bytes

Response time histogram:
0.001 [1]	|
0.087 [999795]	|■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
0.174 [94]	|
0.260 [22]	|
0.347 [18]	|
0.433 [18]	|
0.520 [0]	|
0.606 [15]	|
0.693 [34]	|
0.780 [2]	|
0.866 [1]	|


Latency distribution:
10%% in 0.0024 secs
25%% in 0.0031 secs
50%% in 0.0040 secs
75%% in 0.0053 secs
90%% in 0.0069 secs
95%% in 0.0083 secs
99%% in 0.0134 secs

Details (average, fastest, slowest):
DNS+dialup:	0.0000 secs, 0.0000 secs, 0.0087 secs
DNS-lookup:	0.0000 secs, 0.0000 secs, 0.0684 secs
req write:	0.0000 secs, 0.0000 secs, 0.0266 secs
resp wait:	0.0088 secs, 0.0006 secs, 0.6919 secs
resp read:	0.0001 secs, 0.0000 secs, 0.0617 secs

Status code distribution:
[200]	1000000 responses

# permissions
```
Команда hey:
& "C:\WokStuff\ITMO\NIR-semester-2\loadgen\build\resources\main\hey\hey.exe" -z 180 -c 50 -m GET -H "Authorization: Bearer eyJraWQiOiI4ZjY1MTM3Ni02ZDcyLTQzNzYtYTMyZC1jYzE2NjE1YTZhMGIiLCJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3ODAwNzA2MTYsInN1YiI6ImFsaWNlIiwicm9sZUlkIjpbMSwyXX0.KtHdaJNw08rRPnoeOGIx8HjGkJHcr1RtGX39jgAGb3bB87VIC-V2eT5QDX_rHfByX8eCl9BEUB-SCuQME0DNrxQ1QVMPxwUMV-brjaw0UKfM1nds7jBbMLsVk3DGZVDEW2duL2O6idrI7yC2hQTJM9NHgqlXoVLUdLSii7AVndRmQ9oAna01uHyxFcMD7qAlZTrpGPYchXP8HRDglaJY-3N9WLagekaZ1n7XV7Pu7tTVWCb6FW5mSOnN_edWp1v6IXj374MdjQ6mRagirhl78jt2m4HH0AOIWr-fYz0EgEbM_z2t8X5gdeqrS8FP13lvaAQzL62cSBYnmJdga-PPcQ" "http://localhost:8083/echo/echo?value=load2"


=== PERMISSION ===
URL: http://localhost:8083/echo/echo?value=load2
Throughput: 2482,85 req/s
Latency (avg): 20,10 ms
Latency (p50): 18,50 ms


Summary:
Total:	180.0168 secs
Slowest:	0.5936 secs
Fastest:	0.0022 secs
Average:	0.0201 secs
Requests/sec:	2482.8518

Total data:	2234775 bytes
Size/request:	5 bytes

Response time histogram:
0.002 [1]	|
0.061 [439998]	|■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
0.120 [5487]	|
0.180 [1010]	|
0.239 [339]	|
0.298 [81]	|
0.357 [30]	|
0.416 [6]	|
0.475 [1]	|
0.534 [0]	|
0.594 [2]	|


Latency distribution:
10%% in 0.0057 secs
25%% in 0.0148 secs
50%% in 0.0185 secs
75%% in 0.0229 secs
90%% in 0.0323 secs
95%% in 0.0395 secs
99%% in 0.0739 secs

Details (average, fastest, slowest):
DNS+dialup:	0.0000 secs, 0.0000 secs, 0.0078 secs
DNS-lookup:	0.0000 secs, 0.0000 secs, 0.0080 secs
req write:	0.0000 secs, 0.0000 secs, 0.0099 secs
resp wait:	0.0200 secs, 0.0022 secs, 0.5934 secs
resp read:	0.0000 secs, 0.0000 secs, 0.0136 secs

Status code distribution:
[200]	446955 responses
```
# permissions-cached
```
Команда hey:
& "C:\WokStuff\ITMO\NIR-semester-2\loadgen\build\resources\main\hey\hey.exe" -z 180 -c 50 -m GET -H "Authorization: Bearer eyJraWQiOiIwNzczYzIzMS03NjY2LTQ5N2UtYWIxNS1hNjYyY2U3NDJhM2IiLCJhbGciOiJSUzI1NiJ9.eyJleHAiOjE3ODAwNzAyMDEsInN1YiI6ImFsaWNlIiwicm9sZUlkIjpbMSwyXX0.UuNovrGSXiy7BGhZpuw9p3Rbpto2ioc36x-WJFxxD8YynI6dnzJL0mcnOlReBKEqaPbSaI_A322XfHYBDeVktMnxWF4GFzBwhIknrbnG_W6NZVfPSvG6_h8qCXrZGoxODMriNdnfp9yCm8wbZUdFKwaQ66X-QAuQifVJYkyWrjD7Be2kWJmrLRp_k3v1ubgvwUUhJFeDWlv4wBUHAAXyXYa8mdTfVB1GSunqk3SGdbZ_KYtCl1o2yoBkFyC3iFyhw_bStDy_PbGRr3ceNOh3Xw2vF0A2GcLPprBZCicD3d2_OxdQ9WVWpSPRWcmc_bPE4S8Dd_xCQkyDssNaW5tFOQ" "http://localhost:8083/echo/echo?value=load3"


=== CACHE ===
URL: http://localhost:8083/echo/echo?value=load3
Throughput: 5792,22 req/s
Latency (avg): 9,00 ms
Latency (p50): 7,90 ms


Summary:
Total:	180.0062 secs
Slowest:	0.7391 secs
Fastest:	0.0016 secs
Average:	0.0090 secs
Requests/sec:	5792.2243

Total data:	5213180 bytes
Size/request:	5 bytes

Response time histogram:
0.002 [1]	|
0.075 [999818]	|■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
0.149 [131]	|
0.223 [0]	|
0.297 [0]	|
0.370 [0]	|
0.444 [0]	|
0.518 [0]	|
0.592 [0]	|
0.665 [18]	|
0.739 [32]	|


Latency distribution:
10%% in 0.0052 secs
25%% in 0.0063 secs
50%% in 0.0079 secs
75%% in 0.0099 secs
90%% in 0.0124 secs
95%% in 0.0145 secs
99%% in 0.0237 secs

Details (average, fastest, slowest):
DNS+dialup:	0.0000 secs, 0.0000 secs, 0.0109 secs
DNS-lookup:	0.0000 secs, 0.0000 secs, 0.0084 secs
req write:	0.0000 secs, 0.0000 secs, 0.0145 secs
resp wait:	0.0089 secs, 0.0016 secs, 0.7287 secs
resp read:	0.0000 secs, 0.0000 secs, 0.0235 secs

Status code distribution:
[200]	1000000 responses
```
