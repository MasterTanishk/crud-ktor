class AddPriceToProducts < ActiveRecord::Migration[8.0]
  def change
    add_column :products, :price2, :decimal
  end
end
