json.extract! order, :id, :name, :price, :stock_quantity, :created_at, :updated_at
json.url order_url(order, format: :json)
