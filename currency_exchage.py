import json
from collections import defaultdict

from pip._vendor import requests


class BuySell:
    def __init__(self):
        self.graph = defaultdict(list)

    def get_api_response(self, api):
        response = requests.get(api).text
        return json.loads(response)

    def build_graph(self, response):
        for pair_detail in response:
            self.graph[pair_detail["base_currency"]].append(
                (pair_detail["quote_currency"], float(pair_detail["base_max_size"])))
            self.graph[pair_detail["quote_currency"]].append(
                (pair_detail["base_currency"], 1 / float(pair_detail["base_max_size"])))

    def find_best(self, source, target):

        def backtrack(curr, seen):
            if curr == target:
                return 1
            product = 1
            if curr in self.graph:
                for neighbor, price in self.graph[curr]:
                    if neighbor not in seen:
                        seen.add(neighbor)
                        product = max(product, price * backtrack(neighbor, seen))
                        seen.remove(neighbor)

            return product

        return backtrack(source, {source})


if __name__ == "__main__":
    runner = BuySell()
    response = runner.get_api_response("https://api.pro.coinbase.com/products")
    runner.build_graph(response)
    # print(runner.find_best("BAL", "KNC"))
    print(runner.find_best("BTC", "USDT"))
